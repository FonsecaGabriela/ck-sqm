package com.github.mauricioaniche.ck;


import java.util.Set;
import java.util.Collections;

public class VisibleMethods {
	private Set<CKMethodResult> visibleMethods;

	public Set<CKMethodResult> getVisibleMethods2() {
		return visibleMethods;
	}

	public void setVisibleMethods(Set<CKMethodResult> visibleMethods) {
		this.visibleMethods = visibleMethods;
	}

	public Set<CKMethodResult> getVisibleMethods() {
		return Collections.unmodifiableSet(visibleMethods);
	}

	public int getNumberOfVisibleMethods() {
		return visibleMethods.size();
	}
}