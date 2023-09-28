package com.ua.foxminded.dmgolub.formula1;

import java.time.Duration;

public class Racer {
    
    private final String abbreviation;
    private final String name;
    private final String team;
    private final Duration fastestLap;

    public Racer(String abbreviation, String name, String team, Duration fastestLap) {
        if (abbreviation == null) {
            throw new IllegalArgumentException("racer abbreviation can not be null!");
        }
        if (name == null) {
            throw new IllegalArgumentException("racer name can not be null!");
        }
        if (team == null) {
            throw new IllegalArgumentException("racer team can not be null!");
        }
        if (fastestLap == null) {
            throw new IllegalArgumentException("racer fastest lap duration can not be null!");
        }
        if (fastestLap.equals(Duration.ZERO)) {
            throw new IllegalArgumentException("racer fastest lap duration must be over zero!");
        }
        this.abbreviation = abbreviation;
        this.name = name;
        this.team = team;
        this.fastestLap = fastestLap;
    }
    
    public Duration getFastestLap() {
        return fastestLap;
    }
    
    public String getName() {
        return name;
    }
    
    public String getTeam() {
        return team;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((abbreviation == null) ? 0 : abbreviation.hashCode());
        result = prime * result
                + ((fastestLap == null) ? 0 : fastestLap.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((team == null) ? 0 : team.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Racer other = (Racer) obj;
        if (abbreviation == null) {
            if (other.abbreviation != null)
                return false;
        } else if (!abbreviation.equals(other.abbreviation))
            return false;
        if (fastestLap == null) {
            if (other.fastestLap != null)
                return false;
        } else if (!fastestLap.equals(other.fastestLap))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (team == null) {
            if (other.team != null)
                return false;
        } else if (!team.equals(other.team))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Racer [abbreviation = " + abbreviation + ", name = " + name
            + ", team = " + team + ", lapTimes = " + fastestLap + "]";
    }
}
