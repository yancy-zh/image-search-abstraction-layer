package com.homeprojs.imagesearchabstractionlayer.exception;

/**
 * @author Yao Zhang
 * @date 2023-12-11
 * # @apiNote
 */
public class TempDataFileCantBeDeleted extends RuntimeException {

    public TempDataFileCantBeDeleted(String message) {
        super(message);
    }
}
