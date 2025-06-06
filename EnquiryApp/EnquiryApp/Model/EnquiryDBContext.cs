using Microsoft.EntityFrameworkCore;

namespace EnquiryApp.Model
{
    public class EnquiryDBContext:DbContext
    //class to replicate database context for Enquiry API
    {
        public EnquiryDBContext(DbContextOptions<EnquiryDBContext> options) : base(options)
        {
        }
        public DbSet<EnquiryModel> EnquiryModel { get; set; }
        public DbSet<EnquiryType> EnquiryType { get; set; }
        public DbSet<EnquiryStatus> EnquiryStatuses { get; set; }
        //tells which tables you are going to use in the database
    }
}
