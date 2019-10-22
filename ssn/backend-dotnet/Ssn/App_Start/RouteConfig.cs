using System.Web.Mvc;
using System.Web.Routing;

namespace Ssn
{
    public class RouteConfig
    {
        public static void RegisterRoutes(RouteCollection routes)
        {
            routes.IgnoreRoute("{resource}.axd/{*pathInfo}");


            // we add there 2 rules (for /react and /angular) because in the corresponding frontends
            // we use the "react" and "angular" keywords to calculate the base root of the website
            routes.MapRoute(
                name: "React",
                url: "react",
                defaults: new { controller = "Spa", action = "React", id = UrlParameter.Optional }
            );

            routes.MapRoute(
                name: "Angular",
                url: "angular",
                defaults: new { controller = "Spa", action = "Angular", id = UrlParameter.Optional }
            );

            routes.MapRoute(
                name: "Default",
                url: "{controller}/{action}/{id}",
                defaults: new { controller = "Home", action = "Index", id = UrlParameter.Optional }
            );
        }
    }
}
