using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AMMobile.Models
{
    public class ValidadorRangos
    {

        private EfDbContext db = new EfDbContext();

        private static int DEFICIENTE = 1;



        public int GetRangoHora(int rutaID)
        {
            int rangoHora = DEFICIENTE;

            Ruta ruta = db.Ruta.Find(rutaID);
            TimeSpan timeDiff = DateTime.Now - ruta.FechaRuta;
            int minutos = Convert.ToInt32(timeDiff.TotalMinutes);

            var rangos = from r in db.RangosHora
                         select r;
            
            foreach(RangosHora rango in rangos){
                if (minutos >= rango.Inferior && minutos < rango.Superior)
                {
                    rangoHora = rango.RangosHoraId;
                    break;
                }
            }

            return rangoHora;
        }



        public int GetRangoDistancia(ReporteOE reporte)
        {
            int rangoTotal = DEFICIENTE;
            Ruta ruta = db.Ruta.Find(reporte.RutaID);

            if (ruta.Latitud != null && reporte.Latitud != null && ruta.Latitud.Length != 0 && reporte.Latitud.Length != 0)
            {
                var rangos = from r in db.RangosDistancia
                             select r;

                double distanciaKm = distance(Convert.ToDouble(ruta.Latitud), Convert.ToDouble(ruta.Longitud), Convert.ToDouble(reporte.Latitud), Convert.ToDouble(reporte.Longitud), 'K');
                int distancia = Convert.ToInt32(distanciaKm/1000);

                foreach (RangosDistancia rango in rangos)
                {
                    if (distancia >= rango.Inferior && distancia < rango.Superior)
                    {
                        rangoTotal = rango.RangosDistanciaId;
                        break;
                    }
                }
            }



            return rangoTotal;
        }

        private double distance(double lat1, double lon1, double lat2, double lon2, char unit)
        {
            double theta = lon1 - lon2;
            double dist = Math.Sin(deg2rad(lat1)) * Math.Sin(deg2rad(lat2)) + Math.Cos(deg2rad(lat1)) * Math.Cos(deg2rad(lat2)) * Math.Cos(deg2rad(theta));
            dist = Math.Acos(dist);
            dist = rad2deg(dist);
            dist = dist * 60 * 1.1515;
            if (unit == 'K')
            {
                dist = dist * 1.609344;
            }
            else if (unit == 'N')
            {
                dist = dist * 0.8684;
            }
            return (dist);
        }

        //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        //::  This function converts decimal degrees to radians             :::
        //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        private double deg2rad(double deg)
        {
            return (deg * Math.PI / 180.0);
        }

        //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        //::  This function converts radians to decimal degrees             :::
        //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        private double rad2deg(double rad)
        {
            return (rad / Math.PI * 180.0);
        }
    }
}