package today;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/today")
public class Today extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	SimpleDateFormat format1 = new SimpleDateFormat("yyyy년 MM월dd일");
	SimpleDateFormat format2 = new SimpleDateFormat("HH시 mm분ss초");
	
    public Today() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out = response.getWriter();	

		java.util.Date date = new java.util.Date();
		String days = format1.format(date);
		String times = format2.format(date);
		
		out.print("<html>"
				+"<head>"
				+"    <link href=\"main.css\" rel=\"stylesheet\">\r\n" 
				+"    <link href=\"https://fonts.googleapis.com/css?family=Righteous&display=swap\" rel=\"stylesheet\">\r\n"
				+ "<title>TODAY</title>"
				+ "</head>"
				+ "<body>"
			    + "<div id='div'>"
		        + "<nav>"
		        + "   <ul>"
		        + "       <li><a href='main.html'>HOME</a></li>"
		        + "       <li><a href='aboutME.html'>AboutMe</a></li>"
		        + "       <li><a href='photo.html'>Photo</a></li>"
		        + "        <li><a href='/aboutme/today'>Today</a></li>"
		        + "   </ul>"
		        + "</nav>"
		        + "<section>"
		        + "<br><br>"
				+ "<h1 style='text-align:center'> === TODAY ===</h1>"
				+ "<br>"
				+ "<h3 style='text-align:center'>"+ days + "</h3>"
				+ "<h3 style='text-align:center'>"+ times + "</h3>"         
		        + " </section>"
		        + "<footer>"
		        + "   <a href='https://github.com/alfldl'>https://github.com/alfldl</a><br>"
		        + "   <a>k01058318991@gmail.com</a> "
		        + "</footer>"
		+ "</div>"   
		+ "</body>"
		+"</html>"
);
		
		
		
		
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
