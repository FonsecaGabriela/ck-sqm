package com.github.mauricioaniche.ck;


import java.util.Set;
import java.util.Collections;
import java.util.Optional;

public class Method {
	private Set<CKMethodResult> methods;

	public Set<CKMethodResult> getMethods2() {
		return methods;
	}

	public void setMethods(Set<CKMethodResult> methods) {
		this.methods = methods;
	}

	public Set<CKMethodResult> getMethods() {
		return Collections.unmodifiableSet(methods);
	}

	public Optional<CKMethodResult> getMethod(String methodName) {
		return methods.stream().filter(m -> m.getMethodName().equals(methodName)).findFirst();
	}
}