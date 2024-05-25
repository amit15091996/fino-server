package com.fino.serviceImpl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.fino.configuration.JwtHelpers;
import com.fino.dto.FinoUserDetailsDto;
import com.fino.dto.FinoUserEdit;
import com.fino.entity.FinoUserDetails;
import com.fino.entity.FinoUserRoles;
import com.fino.helpers.AppConstants;
import com.fino.records.UserRecords;
import com.fino.repository.FinoUserDetailsRepository;
import com.fino.repository.FinoUserRolesRepository;
import com.fino.service.UserService;
import com.fino.utils.JavaMailUtil;
import com.fino.utils.RandomPasswordGenerator;
import com.fino.exception.*;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private FinoUserDetailsRepository finoUserDetailsRepository;

	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private JwtHelpers jwtHelpers;

	@Autowired
	private FinoUserRolesRepository finoUserRolesRepository;

	@Autowired
	private JavaMailUtil javaMailUtil;

	@Autowired
	private RandomPasswordGenerator randomPasswordGenerator;

	@Override
	public Map<Object, Object> insertFinoUserDetails(FinoUserDetailsDto finoUserDetailsDto) {
		Map<Object, Object> userSignUpMap = new HashMap<>();
		var finoUserDetails = new FinoUserDetails();
		var finoUserRoles = new FinoUserRoles();
		try {
			finoUserDetails.setDateOfBirth(finoUserDetailsDto.getDateOfBirth());
			finoUserDetails.setEmailId(finoUserDetailsDto.getEmailId());
			finoUserDetails.setFirstName(finoUserDetailsDto.getFirstName());
			finoUserDetails.setLastName(finoUserDetailsDto.getLastName());
			finoUserDetails.setMobileNumber(finoUserDetailsDto.getMobileNumber());
			finoUserDetails.setActive(true);
			finoUserDetails.setPassword(this.passwordEncoder.encode(finoUserDetailsDto.getPassword()));
			finoUserRoles.setRoleName(finoUserDetailsDto.getUserRole());
			finoUserRoles.setRoleDescription(AppConstants.USER_ROLE_DESC + finoUserDetailsDto.getUserRole() + " Role");
			finoUserRoles.setFinoUserDetails(finoUserDetails);
			finoUserDetails.setFinoUserRoles(Arrays.asList(finoUserRoles));
			this.finoUserDetailsRepository.save(finoUserDetails);
			userSignUpMap.put(AppConstants.status, AppConstants.success);
			userSignUpMap.put(AppConstants.statusCode, AppConstants.ok);
			userSignUpMap.put(AppConstants.statusMessage, AppConstants.dataSubmitedsuccessfully);
		} catch (Exception e) {
			throw new BadRequest(e.getMessage());
		}
		return userSignUpMap;
	}

	@Override
	public Map<Object, Object> getAllFinoUsersDetails() {
		Map<Object, Object> userResponseMap = new HashMap<>();
		userResponseMap.put(AppConstants.statusCode, AppConstants.ok);
		userResponseMap.put(AppConstants.status, AppConstants.success);
		userResponseMap.put(AppConstants.statusMessage, AppConstants.dataFetchedSuccesfully);
		userResponseMap.put(AppConstants.response, this.finoUserDetailsRepository.getAllActiveUser().stream().map(user -> {
			return new UserRecords(user.getFirstName()+" "+user.getLastName(), user.getDateOfBirth(),
					user.getMobileNumber(), user.getEmailId(), user.getFinoUserRoles(), user.isEnabled()?"ACTIVE":"INACTIVE");
		}).collect(Collectors.toList()));

		return userResponseMap;

	}

	@Override
	public FinoUserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {

		return this.finoUserDetailsRepository.findByMobileNumber(mobileNumber);
	}

	@Override
	public Map<Object, Object> onUserlogin(String mobileNumber, String password) {
		Map<Object, Object> userLoginMap = new HashMap<>();
		try {
			FinoUserDetails finoUser = this.finoUserDetailsRepository.findByMobileNumber(mobileNumber);
			if (finoUser != null) {
				var jwtToken = this.jwtHelpers.generateToken(finoUser);
				userLoginMap.put(AppConstants.USER_NAME, finoUser.getMobileNumber());
				userLoginMap.put(AppConstants.FULL_NAME, finoUser.getFirstName()+" "+finoUser.getLastName());
				userLoginMap.put(AppConstants.JWT_TOKEN, jwtToken);
				userLoginMap.put(AppConstants.TOKEN_EXPIRATION_IN_MILIS,
						this.jwtHelpers.getExpirationDateFromToken(jwtToken).toInstant().toEpochMilli());
				userLoginMap.put(AppConstants.USER_ROLES, finoUser.getFinoUserRoles().stream().map(role->role.getRoleName()).collect(Collectors.toList()));
				userLoginMap.put(AppConstants.status, AppConstants.success);
				userLoginMap.put(AppConstants.statusCode, AppConstants.ok);
				userLoginMap.put(AppConstants.statusMessage, AppConstants.userLoggedInSuccesfully);
				return userLoginMap;
			} else {
				throw new NotFoundException("Sorry No user found with the given mobile number" + mobileNumber);
			}
		} catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}

	}

	@Override
	public Map<Object, Object> assignANewRole(String mobileNumber, String newRole) {
		Map<Object, Object> userResponseMap = new HashMap<>();
		var finoUserDetails = this.finoUserDetailsRepository.findByMobileNumber(mobileNumber);

		if (finoUserDetails != null) {

			if (!finoUserDetails.getFinoUserRoles().stream()
					.filter(userRoles -> userRoles.getRoleName().equalsIgnoreCase(newRole)).findFirst().isPresent()) {
				try {
					var finoUserRoles = new FinoUserRoles();
					finoUserRoles.setRoleName(newRole);
					finoUserRoles.setRoleDescription(AppConstants.USER_ROLE_DESC + newRole + "Role");
					finoUserRoles.setFinoUserDetails(finoUserDetails);
					this.finoUserRolesRepository.save(finoUserRoles);
					userResponseMap.put(AppConstants.statusCode, AppConstants.ok);
					userResponseMap.put(AppConstants.status, AppConstants.success);
					userResponseMap.put(AppConstants.statusMessage,
							AppConstants.roleUpdatedSuccessFully + "  " + mobileNumber);
				} catch (Exception e) {
					throw new BadRequest(e.getMessage());
				}
			}

			else {
				throw new DataAlreadyPresents(
						"This role " + newRole + " is Already been assigned to the given user " + mobileNumber);
			}

		} else {
			throw new NotFoundException(AppConstants.noRecordFound + mobileNumber);
		}
		return userResponseMap;
	}

	@Override
	public Map<Object, Object> deletePreviousAssignedRole(Long roleId) {
		Map<Object, Object> userResponseMap = new HashMap<>();
		if (this.finoUserRolesRepository.findById(roleId).isPresent()) {
			try {
				this.finoUserRolesRepository.deleteById(roleId);
			} catch (Exception e) {throw new InternalServerError(e.getMessage());}
		
			userResponseMap.put(AppConstants.statusCode, AppConstants.ok);
			userResponseMap.put(AppConstants.status, AppConstants.success);
			userResponseMap.put(AppConstants.statusMessage, AppConstants.dataDeletedSuccesFully);
		} else {
			throw new NotFoundException(AppConstants.noRecordFound + roleId);
		}
		return userResponseMap;
	}

	@Override
	public Map<Object, Object> resetPassword(String mobileNumber, LocalDate dateOfBirth) {

		var finoUserDetails = this.finoUserDetailsRepository.findByMobileNumber(mobileNumber);

		if (finoUserDetails != null && finoUserDetails.getMobileNumber().equalsIgnoreCase(mobileNumber)
				&& finoUserDetails.getDateOfBirth().isEqual(dateOfBirth)) {
			var newPassword = this.randomPasswordGenerator.doGeneratePassword(11);
			Map<Object, Object> mailMap = null;
			try {
				mailMap = this.javaMailUtil.sendTextMail(finoUserDetails.getEmailId(), "Login Credentials",
						finoUserDetails.getFirstName() + " " + finoUserDetails.getLastName(), newPassword);
				this.finoUserDetailsRepository.updateFinoUserPassword(this.passwordEncoder.encode(newPassword),
						mobileNumber);
			} catch (Exception e) {
				throw new InternalServerError(e.getMessage());
			}

			return mailMap;
		}

		else {
			throw new BadRequest("Please check your userName or date of birth and try again...");
		}
	}

	@Override
	public Map<Object, Object> changePassword(String mobileNumber,String oldPassword, String newPassword) {
		var finoUserDetails = this.finoUserDetailsRepository.findByMobileNumber(mobileNumber);
		Map<Object, Object> userResponseMap = new HashMap<>();
		if(finoUserDetails !=null && this.passwordEncoder.matches(oldPassword,finoUserDetails.getPassword())) {
		  
			try {
				this.finoUserDetailsRepository.updateFinoUserPassword(this.passwordEncoder.encode(newPassword), mobileNumber);
				userResponseMap.put(AppConstants.statusCode, AppConstants.ok);
				userResponseMap.put(AppConstants.status, AppConstants.success);
				userResponseMap.put(AppConstants.statusMessage, AppConstants.passwordUpdatedSuccesFully);
				
			}catch (Exception e) {
				throw new InternalServerError(e.getMessage());
			}
			
		return 	userResponseMap;
		}
		else {
			throw new BadRequest("Password didn't match please check your old password and try again...");
		}
	}

	@Override
	public Map<Object, Object> deleteFinoUserDetails(String mobileNumber) {
		Map<Object, Object> userResponseMap = new HashMap<>();

		if (this.finoUserDetailsRepository.findByMobileNumber(mobileNumber) !=null) {
			try{
			this.finoUserDetailsRepository.updateFinoUserStatus(false, mobileNumber);
			}catch(Exception e){throw new InternalServerError(e.getMessage());}
			userResponseMap.put(AppConstants.statusCode, AppConstants.ok);
			userResponseMap.put(AppConstants.status, AppConstants.success);
			userResponseMap.put(AppConstants.statusMessage, AppConstants.dataDeletedSuccesFully);
		} else {
			throw new NotFoundException(AppConstants.noRecordFound + mobileNumber);
		}

		return userResponseMap;
	}

	@Override
	public Map<Object, Object> updateFinoUserDetails(String mobileNumber, FinoUserEdit finoUserEdit) {
		Map<Object, Object> userResponseMap = new HashMap<>();

		if (this.finoUserDetailsRepository.findByMobileNumber(mobileNumber)!=null) {
			try {
				this.finoUserDetailsRepository.updateFinoUserDetails(finoUserEdit.getFirstName(),finoUserEdit.getLastName(),finoUserEdit.getDateOfBirth(),finoUserEdit.getEmailId(),mobileNumber);
						userResponseMap.put(AppConstants.statusCode, AppConstants.ok);
						userResponseMap.put(AppConstants.status, AppConstants.success);
						userResponseMap.put(AppConstants.statusMessage,
						AppConstants.recordUpdatedSuccessFully + mobileNumber);

			} catch (Exception e) {
				throw new BadRequest(e.getMessage());
			}

		} else {
			throw new NotFoundException(AppConstants.noRecordFound + mobileNumber);
		}
		return userResponseMap;
	}

}
