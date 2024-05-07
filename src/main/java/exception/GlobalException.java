package exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import com.fino.helpers.AppConstants;

@RestControllerAdvice
public class GlobalException {

	
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Map<Object, Object>> noDataAvailable(NotFoundException notFoundException,
			WebRequest request) {

		CustomException customException = new CustomException(AppConstants.Not_Found, AppConstants.Not_Found_desc,
				LocalDateTime.now(), notFoundException.getMessage(), request.getDescription(false));
		Map<Object, Object> notFound = new HashMap<>();
		notFound.put(AppConstants.statusCode, customException.getStatusCode());
		notFound.put(AppConstants.status, customException.getStatus());
		notFound.put(AppConstants.timeStamp, customException.getTimestamp().toString());
		notFound.put(AppConstants.statusMessage, customException.getMessage());
		notFound.put(AppConstants.description, customException.getDescription());
		return ResponseEntity.ok(notFound);

	}

	@ExceptionHandler(BadRequest.class)
	public ResponseEntity<Map<Object, Object>> badRequest(BadRequest badRequest, WebRequest request) {

		CustomException badRequestException = new CustomException(AppConstants.Bad_Request, AppConstants.Bad_Request_desc,
				LocalDateTime.now(), badRequest.getMessage(), request.getDescription(false));

		Map<Object, Object> badRequestMap = new HashMap<>();

		badRequestMap.put(AppConstants.statusCode, badRequestException.getStatusCode());
		badRequestMap.put(AppConstants.status, badRequestException.getStatus());
		badRequestMap.put(AppConstants.timeStamp, badRequestException.getTimestamp().toString());
		badRequestMap.put(AppConstants.statusMessage, badRequestException.getMessage());
		badRequestMap.put(AppConstants.description, badRequestException.getDescription());
		return ResponseEntity.ok(badRequestMap);

	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Map<Object, Object>> MethodArgumentNotValid(MethodArgumentNotValidException methodArgument,
			WebRequest request) {
		

		CustomException methodArgumentException = new CustomException(AppConstants.Bad_Request,
				AppConstants.Bad_Request_desc, LocalDateTime.now(), methodArgument.getAllErrors().stream()
						.map(t -> t.getDefaultMessage()).collect(Collectors.toList()).toString(),
				request.getDescription(false));

		Map<Object, Object> methodArgumentMap = new HashMap<>();

		methodArgumentMap.put(AppConstants.statusCode, methodArgumentException.getStatusCode());
		methodArgumentMap.put(AppConstants.status, methodArgumentException.getStatus());
		methodArgumentMap.put(AppConstants.timeStamp, methodArgumentException.getTimestamp().toString());
		methodArgumentMap.put(AppConstants.statusMessage, methodArgumentException.getMessage());
		methodArgumentMap.put(AppConstants.description, methodArgumentException.getDescription());
		return ResponseEntity.ok(methodArgumentMap);

	}

	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public ResponseEntity<Map<Object, Object>> MessageNotReadableExceptionHandler(HttpMessageNotReadableException ex,
			WebRequest request) {

		CustomException messageNotReadable = new CustomException(AppConstants.Bad_Request, AppConstants.Bad_Request_desc,
				LocalDateTime.now(), AppConstants.requestBodyMissing, request.getDescription(false));

		Map<Object, Object> NotReadable = new HashMap<>();

		NotReadable.put(AppConstants.statusCode, messageNotReadable.getStatusCode());
		NotReadable.put(AppConstants.status, messageNotReadable.getStatus());
		NotReadable.put(AppConstants.timeStamp, messageNotReadable.getTimestamp().toString());
		NotReadable.put(AppConstants.statusMessage, messageNotReadable.getMessage());
		NotReadable.put(AppConstants.description, messageNotReadable.getDescription());
		return ResponseEntity.ok(NotReadable);

	}

	@ExceptionHandler(InternalServerError.class)
	public ResponseEntity<Map<Object, Object>> internalServerError(InternalServerError internalServerError,
			WebRequest request) {

		CustomException badRequestException = new CustomException(AppConstants.Internal_Server_Error,
				AppConstants.Internal_Server_Error_desc, LocalDateTime.now(), internalServerError.getMessage(),
				request.getDescription(false));

		Map<Object, Object> internalServerErrorMap = new HashMap<>();

		internalServerErrorMap.put(AppConstants.statusCode, badRequestException.getStatusCode());
		internalServerErrorMap.put(AppConstants.status, badRequestException.getStatus());
		internalServerErrorMap.put(AppConstants.timeStamp, badRequestException.getTimestamp().toString());
		internalServerErrorMap.put(AppConstants.statusMessage, badRequestException.getMessage());
		internalServerErrorMap.put(AppConstants.description, badRequestException.getDescription());
		return ResponseEntity.ok(internalServerErrorMap);

	}

	
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<Map<Object, Object>> responseStatusError(ResponseStatusException responseStatusException,
			WebRequest request) {

		CustomException responseStatus = new CustomException(AppConstants.Bad_Request,
				AppConstants.Bad_Request_desc, LocalDateTime.now(), "Please check your input field as all field's are mandatory  ",
				request.getDescription(false));
		Map<Object, Object> responseStatusMap = new HashMap<>();
		responseStatusMap.put(AppConstants.statusCode, responseStatus.getStatusCode());
		responseStatusMap.put(AppConstants.status, responseStatus.getStatus());
		responseStatusMap.put(AppConstants.timeStamp, responseStatus.getTimestamp().toString());
		responseStatusMap.put(AppConstants.statusMessage, responseStatus.getMessage());
		responseStatusMap.put(AppConstants.description, responseStatus.getDescription());
		return ResponseEntity.ok(responseStatusMap);

	}
	
	
	
}
