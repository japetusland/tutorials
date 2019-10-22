using Microsoft.AspNet.Identity.EntityFramework;
using System.Data.Entity;


namespace Ssn.DAL
{
    public class SsnDbContext : IdentityDbContext<IdentityUser>
    {
        public SsnDbContext() : base("DefaultConnection") { }

        public DbSet<Post> Posts { get; set; }
        public DbSet<Comment> Comments { get; set; }
    }
}