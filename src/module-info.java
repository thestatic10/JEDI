/**
 * Module definition for the FlipFit JEDI project.
 */
module JEDI {
    // Standard library requirement for JDBC
    requires java.sql;      
    
    // Exports packages so Javadoc can document them
    exports com.flipkart.bean;
    exports com.flipkart.business;
    exports com.flipkart.client;
    exports com.flipkart.constants;
    exports com.flipkart.dao;
    exports com.flipkart.exceptions;
    exports com.flipkart.utils;
}