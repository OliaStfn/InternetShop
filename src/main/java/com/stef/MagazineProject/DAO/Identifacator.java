package com.stef.MagazineProject.DAO;

import java.io.Serializable;

public interface Identifacator<PK extends Serializable> {
    int getId();
}