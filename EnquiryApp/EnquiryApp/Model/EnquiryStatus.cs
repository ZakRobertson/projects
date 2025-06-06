using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace EnquiryApp.Model
{
    public class EnquiryStatus
    {
        [Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)] 
        public int statusId { get; set; }
        public string statusName { get; set; } = string.Empty;
        // copies columns from EnquiryStatus table in database
    }
}
