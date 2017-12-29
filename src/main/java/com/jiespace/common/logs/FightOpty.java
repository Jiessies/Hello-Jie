package com.jiespace.common.logs;

import com.jiespace.common.enums.OptType;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by huangmingjie on 2017/10/17.
 */
public class FightOpty {
    private OptType optType;
    private String requestId = StringUtils.EMPTY;
    
    public FightOpty(OptType optType, String requestId) {
        this.optType = optType;
        this.requestId = requestId;
    }

	public OptType getOptType() {
		return optType;
	}

	public void setOptType(OptType optType) {
		this.optType = optType;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
    
}
