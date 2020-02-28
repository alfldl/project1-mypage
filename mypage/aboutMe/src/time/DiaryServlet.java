package time;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import java.text.*;

@WebServlet("/Diary")
public class DiaryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private int year=2012, month=9, day=4;    
    private String[] strWeek={
            "일", "월", "화", "수", "목", "금", "토"
    };    
    private int[] lastDay={
            31,28,31,30,31,30,
            31,31,30,31,30,31
    };
    
    public DiaryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=euc-kr");
        PrintWriter out = response.getWriter(); 

        String strYear=request.getParameter("year");
        String strMonth=request.getParameter("month");
        
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M");
        // yyyy-MM-dd 2012-09-04 yyyy-M-d 2012-9-4
        StringTokenizer st = new StringTokenizer(sdf.format(date),"-");
        
        
        // strYear와 strMonth가 null값일때 현재 달로 넣어줌
        if(strYear==null)
        {
            strYear=st.nextToken();
        }
        if(strMonth==null)
        {
            strMonth=st.nextToken();
        }
        
        year = Integer.parseInt(strYear);
        month = Integer.parseInt(strMonth); 

        out.println("<html>");
        
        out.println("<head>");
        out.println("<style type='text/css'>");        
        out.println("#diary{");
        out.println("background-image:url('image/back.jpg');");
        out.println("}");
        out.println("td{");
        out.println("font-famail:휴먼매직체;");
        out.println("}");
        out.println("</style>");
        
        out.println("<script>");
        out.println("function change(){");
        out.println("document.frm.submit();");
        out.println("}");        
        out.println("</script>");
        
        out.println("</head>");
        
        out.println("<body>");
        out.println("<center>");
        
        out.println("<h1>"+year+"년 "+month+"월<h1>");
        
        out.println("<form name=frm action=DiaryServlet>");
        out.println("<table border=0 width=560>");
        out.println("<tr>");
        out.println("<td align=left>");        
        out.println("<select name=year onchange=change()>");
        
        for(int i=2010; i<=2020; i++)
        {
            if(i==year)
            {
                out.println("<option selected>"+i+"</option>");
            }
            else
            {
                out.println("<option>"+i+"</option>");
            }
        }
        out.println("</select>년도&nbsp;&nbsp;&nbsp;");
        
    
        out.println("<select name=month onchange=change()>");
        for(int i=1; i<=12; i++)
        {
            if(i==month)
            {
                out.println("<option selected>"+i+"</option>");
            }
            else
            {
                out.println("<option>"+i+"</option>");
            }
        }
        out.println("</select>월");        
        
        out.println("</form>");
        
        
        out.println("</td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<tr bgcolor=#ccccff>");
        
        // 요일출력 start
        out.println("<table border=1 width=560 bordercolor=#ccccff>");
        out.println("<tr bgcolor=#ccccff>");
        String color="black";
        for(int i=0; i<7; i++)
        {
            if(i==0)
                color="red";
            else if(i==6)
                color="blue";
            else
                color="black";
            out.println("<td align=center width=80 height=40>");
            out.println("<font color="+color+"><b>" +strWeek[i]+"</b></font>");
            out.println("</td>");
        }
        out.println("</tr>");
        out.println("</table>");
        // 요일 출력 end
        
        // 달력 출력 start
        int total=(year-1)*365+(year-1)/4-(year-1)/100+(year-1)/400;
        // --------- 전년도 2011.12.31일까지 총합을 구함
        
        if((year%4==0 && year%100!=0) || (year%400==0))
        {
            lastDay[1]=29;
        }
        else
        {
            lastDay[1]=28;
        }
        
        for(int i=0; i<month-1; i++)
        {
            total+=lastDay[i];
        }
        //  ------- 전달까지 2012. 8. 31일까지 총합을 구함
        total++;
        // ------- 2012. 9. 1일까지 총합
        
        int week=total%7; // 9.1일자의 요일을 구할수 있음
        
        out.println("<table id=diary border=1 width=560 bordercolor=#ccccff>");
        
        for(int i=1; i<=lastDay[month-1]; i++)
        {
            if(week==0)
                color="red";
            else if(week==6)
                color="blue";
            else
                color="black";
            if(i==1)
            {
                out.println("<tr>");
                for(int j=0; j<week; j++)
                {
                    out.println("<td width=80 height=60 align=left valign=top>");
                    out.println("&nbsp;</td>");
                }
                    
            }
            out.println("<td width=80 height=60 align=left valign=top>");
            out.println("<font color="+color+"><b>");
            out.println(i+"</font></td>");        

            week++;            
            if(week>6)
            {
                week=0;
                out.println("</tr>");
                out.println("<tr>");
            }
        }
        
        out.println("</tr></table>");        
        out.println("</table>");

        out.println("</center>");
        out.println("</body>");
        out.println("</html>");
        
    }
}
