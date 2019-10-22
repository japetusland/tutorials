using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;

namespace Ssn.DAL
{
    public class PostDao
    {
        public void InsertPost(Post post)
        {
            using (var context = new SsnDbContext())
            {
                context.Posts.Add(post);
                context.SaveChanges();
            }
        }

        public void UpdatePost(int postId, string content)
        {
            using (var context = new SsnDbContext())
            {
                using (var transaction = context.Database.BeginTransaction())
                {
                    var query = from p in context.Posts
                                where p.Id == postId
                                select p;

                    Post foundPost = query.FirstOrDefault();
                    if (foundPost != null)
                    {
                        foundPost.Content = content;
                        context.SaveChanges();
                        transaction.Commit();
                    }
                }
            }
        }

        public List<Post> GetPostsByUser(string user)
        {
            using (var context = new SsnDbContext())
            {
                var query = (from p in context.Posts
                             where p.User == user
                             orderby p.Timestamp descending
                             select p).Include(x => x.Comments);

                return query.ToList();
            }
        }

        public Post GetPost(int postId)
        {
            using (var context = new SsnDbContext())
            {
                var query = (from p in context.Posts
                             where p.Id == postId
                             select p).Include(x => x.Comments);

                return query.FirstOrDefault();
            }
        }

        public void InsertComment(int postId, Comment comment)
        {
            using (var context = new SsnDbContext())
            {
                using (var transaction = context.Database.BeginTransaction())
                {
                    var query = from p in context.Posts
                                where p.Id == postId
                                select p;

                    Post post = query.FirstOrDefault();
                    if (post != null)
                    {
                        post.Comments.Add(comment);
                        context.SaveChanges();
                    }
                    transaction.Commit();
                }
            }
        }

        public List<Comment> GetCommentsByPost(int postId)
        {
            var post = GetPost(postId);
            return post.Comments;
        }
    }
}