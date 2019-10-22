using Ssn.Core.Entities;
using Ssn.Core.Interfaces;
using Ssn.Core.Interfaces.Repository;
using Ssn.Core.Interfaces.Common;
using System;
using System.Collections.Generic;

namespace Ssn.Core.Services
{
    public class PostManager : IPostManager
    {
        private readonly IPostRepository postRepository;
        private readonly ILoggerManager logger;

        public PostManager(IPostRepository postRepository, ILoggerManager logger)
        {
            this.postRepository = postRepository;
            this.logger = logger;
        }

        private long GetTimestamp()
        {
            return DateTimeOffset.UtcNow.ToUnixTimeMilliseconds();
        }

        public bool AddPost(string user, string content)
        {
            try
            {
                postRepository.InsertPost(user, content, GetTimestamp());
                return true;
            }
            catch (Exception ex)
            {
                logger.Error(ex.ToString());
                return false;
            }
        }

        public bool UpdatePost(int id, string content)
        {
            try
            {
                postRepository.UpdatePost(id, content);
                return true;
            }
            catch (Exception ex)
            {
                logger.Error(ex.ToString());
                return false;
            }
        }

        public List<Post> GetPosts(string user)
        {
            var result = new List<Post>();
            try
            {
                result = postRepository.GetPostsByUser(user);
            }
            catch (Exception ex)
            {
                logger.Error(ex.ToString());
            }
            return result;
        }

        public List<Comment> GetComments(int postId)
        {
            var result = new List<Comment>();
            try
            {
                result = postRepository.GetCommentsByPost(postId);
            }
            catch (Exception ex)
            {
                logger.Error(ex.ToString());
            }
            return result;
        }

        public bool AddComment(int postId, string user, string content)
        {
            try
            {
                var comment = new Comment { PostId = postId, User = user, Content = content, Timestamp = GetTimestamp() };
                postRepository.InsertComment(comment);
                return true;
            }
            catch (Exception ex)
            {
                logger.Error(ex.ToString());
                return false;
            }
        }
    }
}
