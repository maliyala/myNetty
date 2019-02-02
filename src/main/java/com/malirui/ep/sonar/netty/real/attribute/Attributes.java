package com.malirui.ep.sonar.netty.real.attribute;

import com.malirui.ep.sonar.netty.real.session.Session;
import io.netty.util.AttributeKey;

public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
