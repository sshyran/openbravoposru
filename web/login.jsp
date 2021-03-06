<%--
   Openbravo POS is a point of sales application designed for touch screens.
   Copyright (C) 2007-2009 Openbravo, S.L.
   http://sourceforge.net/projects/openbravopos

    This file is part of Openbravo POS.

    Openbravo POS is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Openbravo POS is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Openbravo POS.  If not, see <http://www.gnu.org/licenses/>.
 --%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name = "viewport" content = "user-scalable=no, width=device-width">
        <title><bean:message key="welcome.title"/></title>
        <link rel=StyleSheet href="layout.css" type='text/css' media=screen>
        <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
        <link rel="icon" href="favicon.ico" type="image/x-icon" />        
        <html:base/>
    </head>
    <body>
        <center>
            <a href="http://www.openbravo.com/"><img src="images/logo.gif" alt="Openbravo" class="logo" /></a>
        </center>
        <logic:messagesPresent >
            <html:messages id="msg">
                <p>
                    <strong><center><font color="red" size="-1"><bean:write name="msg" /></font></center></strong>
                </p>
            </html:messages>
        </logic:messagesPresent>
        <html:form action="login.do" method="post">
            <center>
            <table class="pad">
                <tbody>
                    <tr>
                        <td ><bean:message key="message.login" /></td>
                    </tr>
                    <tr>
                        <td><html:text property="login" size="13"/></td>
                    </tr>
                    <tr>
                        <td><bean:message key="message.password" /></td>
                    </tr>
                    <tr>
                        <td><html:password property="password" size="13"/></td>
                    </tr>
                    <tr>
                        <td><center><html:submit style="width:100px;"><bean:message key="button.login" /></html:submit></center></td>
                    </tr>
                </tbody>
            </table>
            </center>
        </html:form>
    </body>
</html>
