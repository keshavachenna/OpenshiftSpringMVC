package gov.lacounty.isd.ggsd.mws.web.api;

import java.io.IOException;
import java.security.KeyStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ams.csf.common.CSFManager;
import com.cgi.cpf.util.EncryptionUtil;

/**
 * Servlet implementation class CsfTokenServlet
 */
public class CsfTokenServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CsfTokenServlet()
    {
	super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException
    {
	try
	{
	    CSFManager
		    .getInstance()
		    .getPreferences()
		    .setPreference(null, "TOKEN_IMPL_CLASS",
			    "com.ams.csf.auth.CSFInternalToken");

	    String lsUserid = (String) request.getParameter("userid");
	    lsUserid = (lsUserid == null) ? "e609981" : lsUserid;

	    // KeyStore loKeystore = KeyStore.getInstance("JCEKS", "SunJCE");
	    String lsCsfToken = EncryptionUtil.createCSFToken(lsUserid);

	    response.getWriter().println(lsCsfToken);
	}
	catch (Exception loExp)
	{
	    loExp.printStackTrace();
	    response.getWriter().println("Cannot generate CSF token.");
	}
    }

}
