using System;
using System.Web;
using System.Web.Mvc;
using System.Web.Routing;
using System.Web.Http;
using NLog;
using Newtonsoft.Json.Serialization;

namespace Ssn
{
    public class Global : HttpApplication
    {
        public const string ROLE_USER = "USER";
        public const string CSRF_KEY = "RequestVerificationToken";

        public static Logger Logger { get; private set; }


        void Application_Start(object sender, EventArgs e)
        {
            Logger = LogManager.GetCurrentClassLogger();

            // Code that runs on application startup
            AreaRegistration.RegisterAllAreas();
            GlobalConfiguration.Configure(WebApiConfig.Register);
            RouteConfig.RegisterRoutes(RouteTable.Routes);

            GlobalConfiguration.Configuration
                 .Formatters
                 .JsonFormatter
                 .SerializerSettings
                 .ContractResolver = new CamelCasePropertyNamesContractResolver();
        }
    }
}