using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Ssn.Infrastructure.Data
{
    public class Comment
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }

        public string User { get; set; }
        public int PostId { get; set; }
        public string Content { get; set; }
        public long Timestamp { get; set; }
    }
}
