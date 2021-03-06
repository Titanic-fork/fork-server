package com.titanic.fork.domain.goal;

import com.titanic.fork.web.dto.request.goal.CreateGoalRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Location {

    private String address;
    private Double longitude;
    private Double latitude;

    @Builder
    public Location(String address, Double longitude, Double latitude) {
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public static Location from(CreateGoalRequest createGoalRequest) {
        return Location.builder()
                .address(createGoalRequest.getAddress())
                .latitude(createGoalRequest.getLatitude())
                .longitude(createGoalRequest.getLongitude())
                .build();
    }

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
