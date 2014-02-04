package jobs;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(servletNames = "Quartz Servlet")
public class QuartzJbos implements Filter {

	@Override
	public void destroy() {
		try {
			
			Agendador.para();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		try {
			
			Agendador.inicia();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
