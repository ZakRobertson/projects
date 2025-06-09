using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace EnquiryApp.Model
{
    [Table("Enquiry")] //have to specify the table name b/c it differs from the class name
    public class Enquiry
    {
        [Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int enquiryId { get; set; }
        public int enquiryTypeId { get; set; } 
        public int enquiryStatusId { get; set; }
        public string customerName { get; set; } = string.Empty;
        public string mobileNo { get; set; } = string.Empty;
        public string email { get; set; } = string.Empty;
        public string enquiryMessage { get; set; } = string.Empty;
        public DateTime createdDate { get; set; }
        public string resolution { get; set; } = string.Empty;
    }
    // This class replicates the Enquiry table in the database
}
