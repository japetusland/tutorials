namespace Ssn.DAL
{
    public class Comment
    {
        public int Id { get; set; }

        public string User { get; set; }
        public string Content { get; set; }
        public long Timestamp { get; set; }
    }
}