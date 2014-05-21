package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.CssFilter;
import io.github.seleniumquery.selector.DriverSupportService;
import io.github.seleniumquery.selector.SelectorUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FirstChildPseudoClass implements PseudoClass {

	private static final FirstChildPseudoClass instance = new FirstChildPseudoClass();
	public static FirstChildPseudoClass getInstance() {
		return instance;
	}
	private FirstChildPseudoClass() { }
	
	private static final String FIRST_CHILD_PSEUDO_CLASS_NO_COLON = "first-child";
	private static final String FIRST_CHILD_PSEUDO_CLASS = ":"+FIRST_CHILD_PSEUDO_CLASS_NO_COLON;

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return FIRST_CHILD_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		WebElement parent = SelectorUtils.parent(element);
		// parent is null when element is <HTML>
		if (parent == null) {
			return false;
		}
		return SelectorUtils.itselfWithSiblings(element).get(0).equals(element);
	}

	private static final CssFilter firstChildPseudoClassFilter = new PseudoClassFilter(getInstance());

	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:first-child
		if (DriverSupportService.getInstance().supportsNatively(driver, FIRST_CHILD_PSEUDO_CLASS)) {
			return CompiledCssSelector.createNoFilterSelector(FIRST_CHILD_PSEUDO_CLASS);
		}
		return CompiledCssSelector.createFilterOnlySelector(firstChildPseudoClassFilter);
	}

}