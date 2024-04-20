package com.pluralsight.courseinfo.cli;

import com.pluralsight.courseinfo.cli.service.CourseRetrievalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CourseRetriever {

    private static final Logger LOG = LoggerFactory.getLogger(CourseRetriever.class);

    public static void main(String[] args) {
        LOG.info("Starting CourseRetriever...");

        if (args.length == 0) {
            LOG.warn("Please provide an author name as first argument.");
            return;
        }

        try {
        retrieveCourses(args[0]); //original one line code, add try catch
        } catch (Exception e) {
            LOG.error("Unable to retrieve courses from database.", e);
            //e.printStackTrace(); //we can delete this since logger have the throwable e
        }

    }

    private static void retrieveCourses(String authorId) {
        LOG.info("Retrieving courses for '{}'", authorId);
        CourseRetrievalService courseRetrievalService = new CourseRetrievalService();

        String coursesToStore = courseRetrievalService.getCourserFor(authorId);
        LOG.info("Found {} courses", coursesToStore);
    }
}
