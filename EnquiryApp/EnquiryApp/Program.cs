using Microsoft.EntityFrameworkCore;
using EnquiryApp.Model;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
builder.Services.AddCors();
builder.Services.AddCors(options =>
{
    options.AddPolicy("AllowCors",
        builder => builder.WithOrigins("https://localhost:4200", "http://localhost:4200") // Allow requests from these origins
                          .AllowAnyMethod() // Allow any HTTP method (GET, POST, PUT, DELETE, etc.)
                          .AllowAnyHeader() // Allow any header
                          .AllowCredentials()); // Allow credentials (cookies, authorization headers, etc.)
});

builder.Services.AddDbContext<EnquiryDBContext>(options =>
    options.UseSqlServer(builder.Configuration.GetConnectionString("EnquiryCon")));
// Register the DbContext by reading the connection string and assinging it to the options of the DbContext class



var app = builder.Build();


app.UseHttpsRedirection();

app.UseAuthorization();
app.UseCors("AllowCors");

app.MapControllers();

app.Run();
