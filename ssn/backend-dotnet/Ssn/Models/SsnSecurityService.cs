using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.EntityFramework;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Claims;

namespace Ssn.Models
{
    public class SsnSecurityService
    {
        public enum UserCreationResult
        {
            SUCCESS,
            USER_ALREADY_EXISTS,
            FAILURE
        }


        public ClaimsIdentity Identity { get; private set; }

        public bool Login(string email, string password)
        {
            try
            {
                var userStore = new UserStore<IdentityUser>();
                var manager = new UserManager<IdentityUser>(userStore);
                var user = manager.Find(email, password);

                if (user == null)
                    return false;

                Identity = manager.CreateIdentity(user, DefaultAuthenticationTypes.ApplicationCookie);
                return true;
            }
            catch (Exception ex)
            {
                Global.Logger.Error(ex.ToString());
                return false;
            }
        }

        public void Logout()
        {
            Identity = null;
        }

        public IList<string> GetRoles(string email)
        {
            try
            {
                var userStore = new UserStore<IdentityUser>();
                var manager = new UserManager<IdentityUser>(userStore);
                return manager.GetRoles(email);
            }
            catch (Exception ex)
            {
                Global.Logger.Error(ex.ToString());
                return null;
            }
        }

        public UserCreationResult CreateUser(string email, string password)
        {
            try
            {
                var userStore = new UserStore<IdentityUser>();
                var manager = new UserManager<IdentityUser>(userStore);
                var user = new IdentityUser() { UserName = email };

                if (manager.FindByName(email) != null)
                    return UserCreationResult.USER_ALREADY_EXISTS;

                IdentityResult result = manager.Create(user, password);
                if (result.Succeeded)
                {
                    if (CreateRole(Global.ROLE_USER))
                    {
                        result = manager.AddToRole(user.Id, Global.ROLE_USER);
                        if (!result.Succeeded)
                            Global.Logger.Error(result.Errors.FirstOrDefault());
                    }
                    else
                    {
                        Global.Logger.Error("Cannot create role");
                    }
                }
                else
                    Global.Logger.Error(result.Errors.FirstOrDefault());

                if (result.Succeeded)
                    return UserCreationResult.SUCCESS;

                return UserCreationResult.FAILURE;
            }
            catch (Exception ex)
            {
                Global.Logger.Error(ex.ToString());
                return UserCreationResult.FAILURE;
            }
        }

        public bool CreateRole(string role)
        {
            try
            {
                if (GetRoles().Contains(role))
                    return true;
                var roleStore = new RoleStore<IdentityRole>();
                var roleManager = new RoleManager<IdentityRole>(roleStore);
                var result = roleManager.Create(new IdentityRole(role));
                if (!result.Succeeded)
                    Global.Logger.Error(result.Errors.FirstOrDefault());
                if (result.Succeeded)
                    return true;
                return false;
            }
            catch (Exception ex)
            {
                Global.Logger.Error(ex.ToString());
                return false;
            }
        }

        public List<string> GetUsers()
        {
            List<string> result = new List<string>();
            try
            {
                var userStore = new UserStore<IdentityUser>();
                var manager = new UserManager<IdentityUser>(userStore);
                result = manager.Users.Select(iu => iu.UserName).ToList();
            }
            catch (Exception ex)
            {
                Global.Logger.Error(ex.ToString());
            }
            return result;
        }

        public List<string> GetRoles()
        {
            List<string> result = new List<string>();
            try
            {
                var roleStore = new RoleStore<IdentityRole>();
                var roleManager = new RoleManager<IdentityRole>(roleStore);
                result = roleManager.Roles.Select(r => r.Name).ToList();
            }
            catch (Exception ex)
            {
                Global.Logger.Error(ex.ToString());
            }
            return result;
        }

        public bool AddUserToRole(string email, string roleName)
        {
            try
            {
                var userStore = new UserStore<IdentityUser>();
                var manager = new UserManager<IdentityUser>(userStore);
                var user = manager.FindByName(email);
                var result = manager.AddToRole(user.Id, roleName);
                if (result.Succeeded)
                    return true;
                return false;
            }
            catch (Exception ex)
            {
                Global.Logger.Error(ex.ToString());
                return false;
            }
        }

        public bool RemoveUserFromRole(string email, string roleName)
        {
            try
            {
                var userStore = new UserStore<IdentityUser>();
                var manager = new UserManager<IdentityUser>(userStore);
                manager.RemoveFromRole(email, roleName);
                return true;
            }
            catch (Exception ex)
            {
                Global.Logger.Error(ex.ToString());
                return false;
            }
        }

        public IdentityUser GetUser(string email)
        {
            try
            {
                var userStore = new UserStore<IdentityUser>();
                var manager = new UserManager<IdentityUser>(userStore);
                return manager.FindByName(email);
            }
            catch (Exception ex)
            {
                Global.Logger.Error(ex.ToString());
                return null;
            }
        }
    }
}