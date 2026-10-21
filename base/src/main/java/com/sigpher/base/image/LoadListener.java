package com.sigpher.base.image;

/**
 * @author Aaron aaronzheng9603@gmail.com
 */
public interface LoadListener {

    boolean onSuccess(Object resource);

    boolean onFailure(Throwable e);
}
