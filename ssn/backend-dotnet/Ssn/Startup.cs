using Ssn.DAL;
using Microsoft.AspNet.Identity;
using Microsoft.Owin;
using Microsoft.Owin.Security.Cookies;
using Owin;
using System;
using System.Data.Entity;

[assembly: OwinStartupAttribute(typeof(Ssn.Startup))]
namespace Ssn
{
    public class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            try
            {
                // check if db needs to be deleted
                var initializer = new DropCreateDatabaseIfModelChanges<SsnDbContext>();
                Database.SetInitializer(initializer);
                initializer.InitializeDatabase(new SsnDbContext());
            }
            catch (Exception ex)
            {
                Global.Logger.Error(ex.ToString());
            }


            app.UseCookieAuthentication(new CookieAuthenticationOptions
            {
                AuthenticationType = DefaultAuthenticationTypes.ApplicationCookie,
                LoginPath = new PathString("/Authentication/Login")
            });
        }
    }
}