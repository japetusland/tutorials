using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Helpers;
using System.Web.Http.Controllers;
using System.Web.Http.Filters;

namespace Ssn.Controllers.Annotations
{

    [AttributeUsage((AttributeTargets.Class | AttributeTargets.Method), AllowMultiple = false, Inherited = false)]
    public class ValidateXsrfTokenAttribute : ActionFilterAttribute
    {
        public override void OnActionExecuting(HttpActionContext actionContext)
        {
            bool ok = false;

            try
            {
                IEnumerable<string> tokenHeaders;
                if (actionContext.Request.Headers.TryGetValues(Global.CSRF_KEY, out tokenHeaders))
                {
                    string[] tokens = tokenHeaders.First().Split(':');
                    if (tokens.Length == 2)
                    {
                        ok = true;
                        var cookieToken = tokens[0].Trim();
                        var formToken = tokens[1].Trim();
                        AntiForgery.Validate(cookieToken, formToken);
                    }
                }
            }
            catch
            {
                ok = false;
            }

            if (!ok)
                actionContext.Response = actionContext.Request.CreateErrorResponse(HttpStatusCode.Forbidden, "Unauthorized request.");
        }
    }

}