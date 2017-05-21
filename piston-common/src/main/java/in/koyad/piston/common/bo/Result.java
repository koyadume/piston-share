package in.koyad.piston.common.bo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import lombok.Getter;

import com.google.common.io.CharStreams;

import in.koyad.piston.common.util.LogUtil;

public class Result {
	
	private static final LogUtil LOGGER = LogUtil.getLogger(Result.class);
	
	@Getter
	private int code;
	
	@Getter
	private InputStream stream;
	
	@Getter
	private String contentType;
	
	private String resBody = null;
	
	public Result(int code, InputStream stream, String contentType) {
		this.code = code;
		this.stream = stream;
		this.contentType = contentType;
	}
	
	public String getBody() {
		if(null ==  resBody) {
			try (final Reader reader = new InputStreamReader(stream)) {
				resBody = CharStreams.toString(reader);
			} catch (IOException ex) {
				LOGGER.logException(ex);
			}
		}
		
		return resBody;
	}
}
