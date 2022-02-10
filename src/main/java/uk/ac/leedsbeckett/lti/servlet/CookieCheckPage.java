/*
 * Copyright 2022 Leeds Beckett University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.ac.leedsbeckett.lti.servlet;

/**
 *
 * @author jon
 */
public class CookieCheckPage
{
  public static String getPage( String insert )
  {
    return PAGE.replaceAll( "INSERT", insert );
  }
  
  public static final String PAGE = 
"        <!DOCTYPE html>\n" +
"        <html lang=\"en\">\n" +
"        <head>\n" +
"        <title></title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <style type=\"text/css\">\n" +
"        body {\n" +
"        font-family: Geneva, Arial, Helvetica, sans-serif;\n" +
"        }\n" +
"        </style>\n" +
"        <script type=\"text/javascript\">\n" +
"        INSERT\n" +
"\n" +
"        function displayLoadingBlock() {\n" +
"            document.getElementById(\"lti1p3-loading-msg\").style.display = \"block\";\n" +
"        }\n" +
"\n" +
"        function displayWarningBlock() {\n" +
"            document.getElementById(\"lti1p3-warning-msg\").style.display = \"block\";\n" +
"            var newTabLink = document.getElementById(\"lti1p3-new-tab-link\");\n" +
"            newTabLink.onclick = function() {\n" +
"                window.open(contentUrl , '_blank');\n" +
"                newTabLink.parentNode.removeChild(newTabLink);\n" +
"            };\n" +
"        }\n" +
"\n" +
"        function checkCookiesAllowed() {\n" +
"            var cookie = \"lti1p3_test_cookie=1; path=/\";\n" +
"            if (siteProtocol === 'https') {\n" +
"                cookie = cookie + '; SameSite=None; secure';\n" +
"            }\n" +
"            document.cookie = cookie;\n" +
"            var res = document.cookie.indexOf(\"lti1p3_test_cookie\") !== -1;\n" +
"            if ( res ) {\n" +
"                // remove test cookie and reload page\n" +
"                document.cookie = \"lti1p3_test_cookie=1; expires=Thu, 01-Jan-1970 00:00:01 GMT\";\n" +
"                displayLoadingBlock();\n" +
"                window.location.href = contentUrl;\n" +
"            } else {\n" +
"                displayWarningBlock();\n" +
"            }\n" +
"        }\n" +
"\n" +
"        document.addEventListener(\"DOMContentLoaded\", checkCookiesAllowed);" +
"        </script>\n" +
"        </head>\n" +
"        <body>\n" +
"        <div id=\"lti1p3-loading-msg\" style=\"display: none;\">\n" +
"        Loading...\n" +
"        </div>\n" +
"        <div id=\"lti1p3-warning-msg\" style=\"display: none;\">\n" +
"        It appears that your browser is blocking cookies. This will be because \n" + 
"        of security settings. Please seek advice about enabling this web site to \n" +
"        set a cookie within an iframe. \n " +
"        <p><strong>Unable to automatically forward you to the requested content. " +
"        Please try using this link:</strong> " +
"        <a href=\"javascript: void(0);\" id=\"lti1p3-new-tab-link\">Continue</a></p>\n" +
"        </div>\n" +
"        </body>\n" +
"        </html>";
  
}
