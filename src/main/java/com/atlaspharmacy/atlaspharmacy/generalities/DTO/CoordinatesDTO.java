package com.atlaspharmacy.atlaspharmacy.generalities.DTO;

public class CoordinatesDTO {
    private Long id;
    private Long longitute;
    private Long latitude;

    public CoordinatesDTO() {
    }

    public CoordinatesDTO(Long id, Long longitute, Long latitude) {
        this.id = id;
        this.longitute = longitute;
        this.latitude = latitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLongitute() {
        return longitute;
    }

    public void setLongitute(Long longitute) {
        this.longitute = longitute;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }
}
