package com.company.project.constants;

public final class Constant {

    /**
     * Prefix for OSX, MS and Linux
     */
    public static final String OS_PREFIX;

    static {
        if (OSinfo.isWindows()) {
            OS_PREFIX = "D:\\";
        } else if (OSinfo.isLinux()) {
            OS_PREFIX = "/home/java/file/";
        } else if (OSinfo.isMacOS()) {
            OS_PREFIX = "/Users/cjm";
        } else {
            OS_PREFIX = "D:\\";
        }
    }

}
