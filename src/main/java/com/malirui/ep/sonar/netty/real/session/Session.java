package com.malirui.ep.sonar.netty.real.session;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author maliruimeituan.com
 * @create 2019-02-02:上午11:44
 */
@Data
@NoArgsConstructor
public class Session {
    //用户唯一性标识
    private String userId;
    private String userName;

    public Session(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return userId+":"+userName;
    }
}
