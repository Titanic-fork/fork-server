package com.titanic.fork.utils.checker;

public class KilometerDistanceChecker implements DistanceChecker {
    @Override
    public double getDistance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(DistanceChecker.deg2rad(lat1)) * Math.sin(DistanceChecker.deg2rad(lat2)) +
                Math.cos(DistanceChecker.deg2rad(lat1)) * Math.cos(DistanceChecker.deg2rad(lat2)) *
                        Math.cos(DistanceChecker.deg2rad(theta));

        dist = Math.acos(dist);
        dist = DistanceChecker.rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist *= 1.609344;

        return dist;
    }

    @Override
    public boolean isPossible(double distance) {
        return (distance > 0.3) ? false : true;
    }
}
