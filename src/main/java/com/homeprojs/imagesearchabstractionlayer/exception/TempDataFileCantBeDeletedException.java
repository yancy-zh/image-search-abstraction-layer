package com.homeprojs.imagesearchabstractionlayer.exception;

/**
 * @author Yao Zhang
 * @date 2023-12-11
 * # @apiNote
 */
public class TempDataFileCantBeDeletedException extends RuntimeException {

    public TempDataFileCantBeDeletedException(String message) {
        super(message);
    }
}
