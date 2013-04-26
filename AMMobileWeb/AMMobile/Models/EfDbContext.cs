using System.Data.Entity;
using System.Data.Entity.ModelConfiguration;
using System.Data.Entity.ModelConfiguration.Conventions;


namespace AMMobile.Models
{
    public class EfDbContext : DbContext
    {

        public DbSet<Usuario> Usuarios { get; set; }
        public DbSet<EstadoRuta> EstadoRuta { get; set; }
        public DbSet<Ruta> Ruta { get; set; }
        public DbSet<EstadoVisita> EstadoVisita { get; set; }
        public DbSet<ReporteOE> ReporteOE { get; set; }
        protected override void OnModelCreating(System.Data.Entity.DbModelBuilder modelBuilder)
        {
            //modelBuilder.Entity<TipoOperacion>().HasKey(b => b.IdTipoOperacion);
            modelBuilder.Conventions.Remove<PluralizingTableNameConvention>();

            modelBuilder.Entity<Usuario>()
                .HasRequired(n => n.TipoRol)
                .WithMany()
                .HasForeignKey(n => n.RolNm);

            modelBuilder.Entity<Ruta>()
                .HasRequired(n => n.Usuario)
                .WithMany()
                .HasForeignKey(n => n.UsuarioID);

            modelBuilder.Entity<Ruta>()
                .HasRequired(n => n.EstadoRuta)
                .WithMany()
                .HasForeignKey(n => n.EstadoRutaID);

            modelBuilder.Entity<ReporteOE>()
                .HasRequired(n => n.Usuario)
                .WithMany()
                .HasForeignKey(n => n.UsuarioID);

            modelBuilder.Entity<ReporteOE>()
                .HasRequired(n => n.EstadoVisita)
                .WithMany()
                .HasForeignKey(n => n.EstadoVisitaID);

            modelBuilder.Entity<ReporteOE>()
                .HasRequired(n => n.Ruta)
                .WithMany()
                .HasForeignKey(n => n.RutaID);
          }

        public DbSet<TipoRol> TipoRoles { get; set; }


    }
}