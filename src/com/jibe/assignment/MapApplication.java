package com.jibe.assignment;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.jibe.assignment.ReadFileServlet;;

public class MapApplication extends Application {
     public Set<Class<?>> getClasses() {
         Set<Class<?>> s = new HashSet<Class<?>>();
         s.add(ReadFileServlet.class);
         return s;
     }
}