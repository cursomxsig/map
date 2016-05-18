/**
 * 
 */
package mx.org.inegi.geo.map.handler;

import java.sql.SQLException;

import mx.org.inegi.geo.map.exception.InvalidProjectException;
import mx.org.inegi.geo.map.exception.InvalidProjectionException;
import mx.org.inegi.geo.map.exception.NoQualifiedFieldsException;
import mx.org.inegi.geo.map.exception.TableNotFoundException;
import mx.org.inegi.geo.map.exception.TimeFrameException;
import mx.org.inegi.geo.map.web.response.ResponseFactory;
import mx.org.inegi.geo.map.web.response.ResponseFactory.UnsuccessfulResponse;

import org.apache.commons.mail.EmailException;
import org.apache.ibatis.exceptions.PersistenceException;
import org.mybatis.spring.MyBatisSystemException;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.mail.MailException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

/**
 * @author femat
 * 
 */
@ControllerAdvice
@EnableWebMvc
public class ErrorHandler {

	@ExceptionHandler(ArrayIndexOutOfBoundsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public UnsuccessfulResponse unsuccessful(ArrayIndexOutOfBoundsException ex) {
		UnsuccessfulResponse ur = ResponseFactory.unsuccessfulResponse(ex
				.getMessage());
		return ur;
	}

	@ExceptionHandler(TimeFrameException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public UnsuccessfulResponse unsuccessful(TimeFrameException ex) {
		UnsuccessfulResponse ur = ResponseFactory.unsuccessfulResponse(ex
				.getMessage());
		return ur;
	}

	@ExceptionHandler(NumberFormatException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public UnsuccessfulResponse unsuccessful(NumberFormatException ex) {
		UnsuccessfulResponse ur = ResponseFactory.unsuccessfulResponse(ex
				.getMessage());
		return ur;
	}

	@ExceptionHandler(InvalidFormatException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public UnsuccessfulResponse unsuccessful(InvalidFormatException ex) {
		UnsuccessfulResponse ur = ResponseFactory.unsuccessfulResponse(ex
				.getMessage());
		return ur;
	}

	@ExceptionHandler({ InvalidProjectException.class,
			InvalidProjectionException.class, NoQualifiedFieldsException.class,
			TableNotFoundException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public UnsuccessfulResponse unsuccessful(TableNotFoundException ex) {
		UnsuccessfulResponse ur = ResponseFactory.unsuccessfulResponse(ex
				.getMessage());
		return ur;
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public UnsuccessfulResponse unsuccessful(HttpMessageNotReadableException ex) {
		UnsuccessfulResponse ur = ResponseFactory.unsuccessfulResponse(ex
				.getMessage());
		return ur;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public UnsuccessfulResponse unsuccessful(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		StringBuilder sb = new StringBuilder();
		for (FieldError error : result.getFieldErrors()) {
			StringBuilder se = new StringBuilder();
			se.append("Field [");
			se.append(error.getField());
			se.append("] ");
			se.append(error.getDefaultMessage());
			se.append("\n");
			sb.append(se.toString());
		}
		String errors = sb.toString();
		return ResponseFactory.unsuccessfulResponse(errors);
	}

	@ExceptionHandler({ UncategorizedSQLException.class,
			BadSqlGrammarException.class, PSQLException.class,
			SQLException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public UnsuccessfulResponse unsuccessful(Exception ex) {
		ex.printStackTrace();
		StringBuilder sb = new StringBuilder("Database error!");
		sb.append("\nPlease contact your administrator.");
		String message = sb.toString();
		return ResponseFactory.unsuccessfulResponse(message);

	}

	@ExceptionHandler(MyBatisSystemException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public UnsuccessfulResponse unsuccessful(MyBatisSystemException ex) {
		StringBuilder sb = new StringBuilder("MB error!");
		sb.append("\nPlease contact your administrator.");
		String message = sb.toString();
		return ResponseFactory.unsuccessfulResponse(message);

	}

	@ExceptionHandler(PersistenceException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public UnsuccessfulResponse unsuccessful(PersistenceException ex) {
		StringBuilder sb = new StringBuilder("Persistence problem!");
		sb.append("\nPlease contact your administrator.");
		String message = sb.toString();
		return ResponseFactory.unsuccessfulResponse(message);

	}

	@ExceptionHandler(CannotGetJdbcConnectionException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public UnsuccessfulResponse unsuccessful(CannotGetJdbcConnectionException ex) {
		StringBuilder sb = new StringBuilder("Could not get connection!");
		sb.append("\nPlease contact your administrator.");
		String message = sb.toString();
		return ResponseFactory.unsuccessfulResponse(message);

	}

	@ExceptionHandler(EmailException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public UnsuccessfulResponse unsuccessful(EmailException ex) {
		UnsuccessfulResponse ur = ResponseFactory
				.unsuccessfulResponse("Correo no enviado" + ex.getMessage());
		return ur;
	}

	@ExceptionHandler(MailException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public UnsuccessfulResponse unsuccessful(MailException ex) {
		UnsuccessfulResponse ur = ResponseFactory
				.unsuccessfulResponse("correo no enviado" + ex.getMessage());
		return ur;
	}

}
