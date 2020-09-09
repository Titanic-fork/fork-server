package com.titanic.fork.domain.value;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@Getter
public class Location {

    private String address;
    private Double longitude;
    private Double latitude;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(getAddress(), location.getAddress()) &&
                Objects.equals(getLongitude(), location.getLongitude()) &&
                Objects.equals(getLatitude(), location.getLatitude());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddress(), getLongitude(), getLatitude());
    }
}
