using Ssn.DAL;
using System;
using System.Collections.Generic;

namespace Ssn.Models
{
    public class PostsManager
    {
        private long GetTimestamp()
        {
            return DateTimeOffset.UtcNow.ToUnixTimeMilliseconds();
        }

        public bool AddPost(string user, string content)
        {
            try
            {
                var postDao = new PostDao();
                Post post = new Post
                {
                    User = user,
                    Content = content,
                    Timestamp = GetTimestamp()
                };
                postDao.InsertPost(post);
                return true;
            }
            catch (Exception ex)
            {
                Global.Logger.Error(ex.ToString());
                return false;
            }
        }

        public bool UpdatePost(int id, string content)
        {
            try
            {
                var postDao = new PostDao();
                postDao.UpdatePost(id, content);
                return true;
            }
            catch (Exception ex)
            {
                Global.Logger.Error(ex.ToString());
                return false;
            }
        }

        public List<PostDto> GetPosts(string user)
        {
            var result = new List<PostDto>();
            try
            {
                var postDao = new PostDao();
                List<Post> posts = postDao.GetPostsByUser(user);
                result = posts.ConvertAll(post => new PostDto(post));
            }
            catch (Exception ex)
            {
                Global.Logger.Error(ex.ToString());
            }
            return result;
        }

        public List<CommentDto> GetComments(int postId)
        {
            var result = new List<CommentDto>();
            try
            {
                var postDao = new PostDao();
                List<Comment> comments = postDao.GetCommentsByPost(postId);
                result = comments.ConvertAll(comment => new CommentDto(postId, comment));
            }
            catch (Exception ex)
            {
                Global.Logger.Error(ex.ToString());
            }
            return result;
        }

        public bool AddComment(int postId, string user, string content)
        {
            try
            {
                var postDao = new PostDao();
                var comment = new Comment
                {
                    User = user,
                    Content = content,
                    Timestamp = GetTimestamp()
                };
                postDao.InsertComment(postId, comment);
                return true;
            }
            catch (Exception ex)
            {
                Global.Logger.Error(ex.ToString());
                return false;
            }
        }
    }
}