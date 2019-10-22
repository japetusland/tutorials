using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;

namespace Ssn.Infrastructure.Data
{
    public class SsnDbContext: IdentityDbContext
    {
        public SsnDbContext(DbContextOptions<SsnDbContext> options)
            : base(options)
        {
        }

        public DbSet<Post> Posts { get; set; }
        public DbSet<Comment> Comments { get; set; }
    }
}
