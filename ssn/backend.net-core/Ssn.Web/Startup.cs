using AutoMapper;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Localization;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Options;
using Ssn.Core.Common;
using Ssn.Core.Interfaces;
using Ssn.Core.Interfaces.Common;
using Ssn.Core.Interfaces.Repository;
using Ssn.Core.Services;
using Ssn.Infrastructure.Data;
using Ssn.Infrastructure.Data.Repositories;
using Ssn.Infrastructure.Logger;
using Ssn.Web.Services;
using System.Globalization;

namespace Ssn.Web
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddSingleton<ILoggerManager, LoggerManager>();
            services.AddDbContext<SsnDbContext>(options =>
                options.UseSqlServer(
                    Configuration.GetConnectionString("DefaultConnection")));
            services.AddDefaultIdentity<IdentityUser>(options =>
            {
                options.SignIn.RequireConfirmedAccount = false;
                options.Password.RequireDigit = false;
                options.Password.RequireLowercase = false;
                options.Password.RequireUppercase = false;
                options.Password.RequireNonAlphanumeric = false;
                options.Password.RequiredLength = 5;
            }).AddRoles<IdentityRole>().AddEntityFrameworkStores<SsnDbContext>();

            // NOTE: activate localization (must to be before services.AddMvc())
            services.AddLocalization();

            services.AddMvc().AddMvcLocalization();

            var supportedCultures = new[] {
                    new CultureInfo("en"),
                    new CultureInfo("it"),
                };

            services.Configure<RequestLocalizationOptions>(options =>
            {
                options.DefaultRequestCulture = new RequestCulture("en");
                options.SupportedCultures = supportedCultures;
                options.SupportedUICultures = supportedCultures;
            });

            services.AddRazorPages().AddRazorRuntimeCompilation();

            // NOTE: configure and activate AutoMapper
            var mappingConfig = new MapperConfiguration(cfg =>
                cfg.AddMaps(new[] {
                    "Ssn.Web",
                    "Ssn.Core",
                    "Ssn.Infrastructure"
                })
            );
            IMapper mapper = mappingConfig.CreateMapper();
            services.AddSingleton(mapper);

            // NOTE: injecting custom classes
            services.AddTransient<IPostRepository, PostRepository>();
            services.AddTransient<IPostManager, PostManager>();
            services.AddTransient<FrontendService>();

            // NOTE: define AntiForgery custom name for request header
            services.AddAntiforgery(options => options.HeaderName = Common.CSRF_KEY);
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(RoleManager<IdentityRole> roleManager, IApplicationBuilder app, IWebHostEnvironment env, SsnDbContext context, ILoggerManager logger)
        {
            try
            {
                context.Database.Migrate();
            }
            catch
            {
                logger.Error("CANNOT CREATE DATABASE");
                return;
            }

            var role = roleManager.FindByNameAsync(Roles.USER).GetAwaiter().GetResult();
            if (role == null)
            {
                role = new IdentityRole(Roles.USER);
                roleManager.CreateAsync(role).GetAwaiter().GetResult();
            }

            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
                app.UseDatabaseErrorPage();
            }
            else
            {
                app.UseExceptionHandler("/Error");
                // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
                app.UseHsts();
            }

            app.UseHttpsRedirection();
            app.UseStaticFiles();

            app.UseRouting();

            app.UseAuthentication();
            app.UseAuthorization();

            app.UseRequestLocalization(app.ApplicationServices.GetRequiredService<IOptions<RequestLocalizationOptions>>().Value);

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();

                endpoints.MapRazorPages();
            });
        }
    }
}
