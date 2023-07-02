package models.put;

import lombok.Getter;

@Getter
public class UserTimeResponse extends UserTime{
    private String updatedAt;

    public UserTimeResponse(String name, String job, String updateAt) {
        super(name, job);
        this.updatedAt = updateAt;
    }
}
