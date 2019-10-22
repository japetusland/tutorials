using AutoMapper;
using Microsoft.EntityFrameworkCore;
using Ssn.Core.Interfaces.Repository;
using System.Collections.Generic;
using System.Linq;

namespace Ssn.Infrastructure.Data.Repositories
{
    public class PostRepository : IPostRepository
    {
        private readonly SsnDbContext dbContext;
        private readonly IMapper mapper;

        public PostRepository(SsnDbContext dbContext, IMapper mapper)
        {
            this.dbContext = dbContext;
            this.mapper = mapper;
        }

        public Core.Entities.Post GetPost(int postId)
        {
            var post = (from p in dbContext.Posts
                        where p.Id == postId
                        select p)
                        .Include(x => x.Comments)
                        .FirstOrDefault();

            return mapper.Map<Core.Entities.Post>(post);
        }

        public List<Core.Entities.Comment> GetCommentsByPost(int postId)
        {
            var post = GetPost(postId);
            return post.Comments;
        }

        public List<Core.Entities.Post> GetPostsByUser(string user)
        {
            var query = (from p in dbContext.Posts
                         where p.User == user
                         orderby p.Timestamp descending
                         select p)
                         .Include(x => x.Comments);

            return query.ToList().ConvertAll(post => mapper.Map<Core.Entities.Post>(post));
        }

        public void InsertPost(string user, string content, long timestamp)
        {
            var post = new Post { User = user, Content = content, Timestamp = timestamp };
            dbContext.Posts.Add(post);
            dbContext.SaveChanges();
        }

        public void UpdatePost(int postId, string content)
        {
            using (var transaction = dbContext.Database.BeginTransaction())
            {
                var query = from p in dbContext.Posts
                            where p.Id == postId
                            select p;

                Post post = query.FirstOrDefault();
                if (post != null)
                {
                    post.Content = content;
                    dbContext.SaveChanges();
                    transaction.Commit();
                }
            }
        }

        public void InsertComment(Core.Entities.Comment commentEntity)
        {
            using (var transaction = dbContext.Database.BeginTransaction())
            {
                var query = from p in dbContext.Posts
                            where p.Id == commentEntity.PostId
                            select p;

                Post post = query.FirstOrDefault();
                if (post != null)
                {
                    var comment = mapper.Map<Comment>(commentEntity);
                    post.Comments.Add(comment);
                    dbContext.SaveChanges();
                    transaction.Commit();
                }
            }
        }
    }
}
