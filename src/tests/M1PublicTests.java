package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import model.cards.ChangeCard;
import model.cards.Color;
import model.cards.HitAndChangeCard;
import model.cards.HitCard;
import model.cards.SkipCard;

@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class M1PublicTests {

	String colorPath = "model.cards.Color";
	String cardPath = "model.cards.Card";
	String colorCardPath = "model.cards.ColorCard";
	String wildCardPath = "model.cards.WildCard";
	String numberCardPath = "model.cards.NumberCard";
	String actionCardPath = "model.cards.ActionCard";
	String hitCardPath = "model.cards.HitCard";
	String skipCardPath = "model.cards.SkipCard";
	String changeCardPath = "model.cards.ChangeCard";
	String hitAndChangeCardPath = "model.cards.HitAndChangeCard";

	String gamePath = "engine.Game";
	String playerPath = "engine.Player";

	String gameActionExceptionPath = "exceptions.GameActionException";
	String unallowedCardExceptionPath = "exceptions.UnallowedCardException";
	String maxCardsDrawnExceptionPath = "exceptions.MaxCardsDrawnException";

	@Test(timeout = 100)
	public void testEnumColor() throws ClassNotFoundException {
		testIsEnum(Class.forName(colorPath));
	}

	@Test(timeout = 1000)
	public void testEnumValuesColor() {
		String[] inputs = { "RED", "GREEN", "BLUE", "YELLOW", "WILD" };
		testEnumValues("Color", colorPath, inputs);
	}

	@Test(timeout = 1000)
	public void testCardIsSuperClassOfColorCard() throws Exception {
		testClassIsSubclass(Class.forName(colorCardPath), Class.forName(cardPath));
	}

	@Test(timeout = 1000)
	public void testCardIsSuperClassOfWildCard() throws Exception {
		testClassIsSubclass(Class.forName(wildCardPath), Class.forName(cardPath));
	}

	@Test(timeout = 1000)
	public void testWildCardIsSuperClassOfChangeCard() throws Exception {
		testClassIsSubclass(Class.forName(changeCardPath), Class.forName(wildCardPath));
	}

	@Test(timeout = 1000)
	public void testWildCardIsSuperClassOfHitAndChangeCard() throws Exception {
		testClassIsSubclass(Class.forName(hitAndChangeCardPath), Class.forName(wildCardPath));
	}

	@Test(timeout = 1000)
	public void testColorCardIsSuperClassOfNumberCard() throws Exception {
		testClassIsSubclass(Class.forName(numberCardPath), Class.forName(colorCardPath));
	}

	@Test(timeout = 1000)
	public void testColorCardIsSuperClassOfActionCard() throws Exception {
		testClassIsSubclass(Class.forName(actionCardPath), Class.forName(colorCardPath));
	}

	@Test(timeout = 1000)
	public void testActionCardIsSuperClassOfHitCard() throws Exception {
		testClassIsSubclass(Class.forName(hitCardPath), Class.forName(actionCardPath));
	}

	@Test(timeout = 1000)
	public void testActionCardIsSuperClassOfSkipCard() throws Exception {
		testClassIsSubclass(Class.forName(skipCardPath), Class.forName(actionCardPath));
	}

	@Test(timeout = 1000)
	public void testGameActionExceptionIsSuperClassOfUnallowedCardException() throws Exception {
		testClassIsSubclass(Class.forName(unallowedCardExceptionPath), Class.forName(gameActionExceptionPath));
	}

	@Test(timeout = 1000)
	public void testGameActionExceptionIsSuperClassOfMaxCardsDrawnException() throws Exception {
		testClassIsSubclass(Class.forName(maxCardsDrawnExceptionPath), Class.forName(gameActionExceptionPath));
	}

	@Test(timeout = 100)
	public void testConstructorGameActionException() throws Exception {

		Class[] inputs = { String.class };
		testConstructorExists(Class.forName(gameActionExceptionPath), inputs);
	}

	@Test(timeout = 100)
	public void testEmptyConstructorGameActionException() throws Exception {

		Class[] inputs = {};
		testConstructorExists(Class.forName(gameActionExceptionPath), inputs);
	}

	@Test(timeout = 1000)
	public void testClassIsSubclassGameActionException() throws Exception {
		testClassIsSubclass(Class.forName(gameActionExceptionPath), Exception.class);
	}

	@Test(timeout = 100)
	public void testConstructorUnallowedCardException() throws Exception {

		Class[] inputs = { String.class };
		testConstructorExists(Class.forName(unallowedCardExceptionPath), inputs);
	}

	@Test(timeout = 100)
	public void testEmptyConstructorUnallowedCardException() throws Exception {

		Class[] inputs = {};
		testConstructorExists(Class.forName(unallowedCardExceptionPath), inputs);
	}

	@Test(timeout = 100)
	public void testConstructorMaxCardsDrawnException() throws Exception {

		Class[] inputs = { String.class };
		testConstructorExists(Class.forName(maxCardsDrawnExceptionPath), inputs);
	}

	@Test(timeout = 100)
	public void testEmptyConstructorMaxCardsDrawnException() throws Exception {

		Class[] inputs = {};
		testConstructorExists(Class.forName(maxCardsDrawnExceptionPath), inputs);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableNameIsPresentInClassPlayer() throws Exception {
		testInstanceVariableIsPresent(Class.forName(playerPath), "name", true);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableNameIsPrivateInClassPlayer() throws Exception {
		testInstanceVariableIsPrivate(Class.forName(playerPath), "name");
	}

	@Test(timeout = 1000)
	public void testGetterForInstanceVariableNameCostExistsInClassPlayer() throws Exception {
		testGetterMethodExistsInClass(Class.forName(playerPath), "getName", String.class, true);
	}

	@Test(timeout = 100)
	public void testInstanceVariablePlayerNameGetterLogic() throws Exception {
		Constructor<?> championConstructor = Class.forName(playerPath).getConstructor(String.class);
		int randomName = (int) (Math.random() * 10) + 1;
		Object c = championConstructor.newInstance("Name_" + randomName);
		testGetterLogic(c, "name", "Name_" + randomName);
	}

	@Test(timeout = 1000)
	public void testSetterForInstanceVariableNameExistsInClassPlayer() throws Exception {
		testSetterMethodExistsInClass(Class.forName(playerPath), "setName", String.class, false);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableHandIsPresentInClassPlayer() throws Exception {
		testInstanceVariableIsPresent(Class.forName(playerPath), "hand", true);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableHandIsPrivateInClassPlayer() throws Exception {
		testInstanceVariableIsPrivate(Class.forName(playerPath), "hand");
	}

	@Test(timeout = 1000)
	public void testGetterForInstanceVariableHandExistsInClassPlayer() throws Exception {
		testGetterMethodExistsInClass(Class.forName(playerPath), "getHand", ArrayList.class, true);
	}

	@Test(timeout = 1000)
	public void testSetterForInstanceVariableHandExistsInClassPlayer() throws Exception {
		testSetterMethodExistsInClass(Class.forName(playerPath), "setHand", ArrayList.class, false);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableColorIsPresentInClassCard() throws Exception {
		testInstanceVariableIsPresent(Class.forName(cardPath), "color", true);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableColorIsPrivateInClassCard() throws Exception {
		testInstanceVariableIsPrivate(Class.forName(cardPath), "color");
	}

	@Test(timeout = 1000)
	public void testGetterForInstanceVariableColorExistsInClassCard() throws Exception {
		testGetterMethodExistsInClass(Class.forName(cardPath), "getColor", Class.forName(colorPath), true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableCardColorGetterLogic() throws Exception {
		Constructor<?> cardConstructor = Class.forName(cardPath).getConstructor(Class.forName(colorPath));
		int r2 = (int) (Math.random() * 5);
		Object color = null;
		if (r2 == 0)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED");
		else if (r2 == 1)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN");
		else if (r2 == 2)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE");
		else if (r2 == 3)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW");
		else
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "WILD");

		Object c = cardConstructor.newInstance(color);
		testGetterLogic(c, "color", color);
	}

	@Test(timeout = 1000)
	public void testSetterForInstanceVariableColorExistsInClassCard() throws Exception {
		testSetterMethodExistsInClass(Class.forName(cardPath), "setColor", Class.forName(colorPath), true);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableHitNumberIsPresentInClassHitAndChangeCard() throws Exception {
		testInstanceVariableIsPresent(Class.forName(hitAndChangeCardPath), "hitNumber", true);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableHitNumberIsPrivateInClassHitAndChangeCard() throws Exception {
		testInstanceVariableIsPrivate(Class.forName(hitAndChangeCardPath), "hitNumber");
	}

	@Test(timeout = 1000)
	public void testGetterForInstanceVariableHitNumberExistsInClassHitAndChangeCard() throws Exception {
		testGetterMethodExistsInClass(Class.forName(hitAndChangeCardPath), "getHitNumber", int.class, true);
	}

	@Test(timeout = 100)
	public void testHitAndChangeCardHitNumberGetterLogic() throws Exception {
		Constructor<?> constructor = Class.forName(hitAndChangeCardPath).getConstructor();
		Object b = constructor.newInstance();
		int randomHit = (int) (Math.random() * 5) + 4;
		testGetterLogic(b, "hitNumber", randomHit);
	}

	@Test(timeout = 1000)
	public void testSetterForInstanceVariableHitNumberExistsInClassHitAndChangeCard() throws Exception {
		testSetterMethodExistsInClass(Class.forName(hitAndChangeCardPath), "setHitNumber", int.class, false);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableHitNumberIsPresentInClassHitCard() throws Exception {
		testInstanceVariableIsPresent(Class.forName(hitCardPath), "hitNumber", true);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableHitNumberIsPrivateInClassHitCard() throws Exception {
		testInstanceVariableIsPrivate(Class.forName(hitCardPath), "hitNumber");
	}

	@Test(timeout = 1000)
	public void testGetterForInstanceVariableHitNumberExistsInClassHitCard() throws Exception {
		testGetterMethodExistsInClass(Class.forName(hitCardPath), "getHitNumber", int.class, true);
	}

	@Test(timeout = 100)
	public void testHitCardHitNumberGetterLogic() throws Exception {
		Constructor<?> constructor = Class.forName(hitCardPath).getConstructor(Class.forName(colorPath));

		int r2 = (int) (Math.random() * 5);
		Object color = null;
		if (r2 == 0)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED");
		else if (r2 == 1)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN");
		else if (r2 == 2)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE");
		else if (r2 == 3)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW");
		else
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "WILD");
		Object b = constructor.newInstance(color);
		int randomHit = (int) (Math.random() * 5) + 1;
		testGetterLogic(b, "hitNumber", randomHit);
	}

	@Test(timeout = 1000)
	public void testSetterForInstanceVariableHitNumberExistsInClassHitCard() throws Exception {
		testSetterMethodExistsInClass(Class.forName(hitCardPath), "setHitNumber", int.class, false);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableNumberIsPresentInClassNumberCard() throws Exception {
		testInstanceVariableIsPresent(Class.forName(numberCardPath), "number", true);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableNumberIsPrivateInClassNumberCard() throws Exception {
		testInstanceVariableIsPrivate(Class.forName(numberCardPath), "number");
	}

	@Test(timeout = 1000)
	public void testGetterForInstanceVariableNumberExistsInClassNumberCard() throws Exception {
		testGetterMethodExistsInClass(Class.forName(numberCardPath), "getNumber", int.class, true);
	}

	@Test(timeout = 100)
	public void testNumberCardNumberGetterLogic() throws Exception {
		Constructor<?> constructor = Class.forName(numberCardPath).getConstructor(int.class, Class.forName(colorPath));

		int r2 = (int) (Math.random() * 5);
		Object color = null;
		if (r2 == 0)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED");
		else if (r2 == 1)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN");
		else if (r2 == 2)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE");
		else if (r2 == 3)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW");
		else
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "WILD");
		int randomNumber = (int) (Math.random() * 10);
		Object b = constructor.newInstance(randomNumber, color);
		testGetterLogic(b, "number", randomNumber);
	}

	@Test(timeout = 1000)
	public void testSetterForInstanceVariableNumberExistsInClassNumberCard() throws Exception {
		testSetterMethodExistsInClass(Class.forName(numberCardPath), "setNumber", int.class, false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableColorIsNotPresentInClassWildCard() throws Exception {
		testInstanceVariableIsPresent(Class.forName(wildCardPath), "color", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableColorIsNotPresentInClassColorCard() throws Exception {
		testInstanceVariableIsPresent(Class.forName(colorCardPath), "color", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableColorIsNotPresentInClassChangeCard() throws Exception {
		testInstanceVariableIsPresent(Class.forName(changeCardPath), "color", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableColorIsNotPresentInClassHitAndChangeCard() throws Exception {
		testInstanceVariableIsPresent(Class.forName(hitAndChangeCardPath), "color", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableColorIsNotPresentInClassNumberCard() throws Exception {
		testInstanceVariableIsPresent(Class.forName(numberCardPath), "color", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableColorIsNotPresentInClassActionCard() throws Exception {
		testInstanceVariableIsPresent(Class.forName(actionCardPath), "color", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableColorIsNotPresentInClassHitCard() throws Exception {
		testInstanceVariableIsPresent(Class.forName(hitCardPath), "color", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableColorIsNotPresentInClassSkipCard() throws Exception {
		testInstanceVariableIsPresent(Class.forName(skipCardPath), "color", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableHitNumberIsNotPresentInClassWildCard() throws Exception {
		testInstanceVariableIsPresent(Class.forName(wildCardPath), "hitNumber", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableHitNumberIsNotPresentInClassColorCard() throws Exception {
		testInstanceVariableIsPresent(Class.forName(colorCardPath), "hitNumber", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableHitNumberIsNotPresentInClassChangeCard() throws Exception {
		testInstanceVariableIsPresent(Class.forName(changeCardPath), "hitNumber", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableHitNumberIsNotPresentInClassNumberCard() throws Exception {
		testInstanceVariableIsPresent(Class.forName(numberCardPath), "hitNumber", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableHitNumberIsNotPresentInClassActionCard() throws Exception {
		testInstanceVariableIsPresent(Class.forName(actionCardPath), "hitNumber", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableHitNumberIsNotPresentInClassCard() throws Exception {
		testInstanceVariableIsPresent(Class.forName(cardPath), "hitNumber", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableHitNumberIsNotPresentInClassSkipCard() throws Exception {
		testInstanceVariableIsPresent(Class.forName(skipCardPath), "hitNumber", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableNumberIsNotPresentInClassWildCard() throws Exception {
		testInstanceVariableIsPresent(Class.forName(wildCardPath), "number", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableNumberIsNotPresentInClassColorCard() throws Exception {
		testInstanceVariableIsPresent(Class.forName(colorCardPath), "number", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableNumberIsNotPresentInClassChangeCard() throws Exception {
		testInstanceVariableIsPresent(Class.forName(changeCardPath), "number", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableNumberIsNotPresentInClassHitAndChangeCard() throws Exception {
		testInstanceVariableIsPresent(Class.forName(hitAndChangeCardPath), "number", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableNumberIsNotPresentInClassActionCard() throws Exception {
		testInstanceVariableIsPresent(Class.forName(actionCardPath), "number", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableNumberIsNotPresentInClassHitCard() throws Exception {
		testInstanceVariableIsPresent(Class.forName(hitCardPath), "number", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableNumberIsNotPresentInClassSkipCard() throws Exception {
		testInstanceVariableIsPresent(Class.forName(skipCardPath), "number", false);
	}

	@Test(timeout = 100)
	public void testConstructorCard() throws ClassNotFoundException {
		Class[] inputs = { Class.forName(colorPath) };
		testConstructorExists(Class.forName(cardPath), inputs);
	}

	@Test(timeout = 100)
	public void testConstructor2Card() throws ClassNotFoundException {
		Class[] inputs = {};
		testConstructorDoesNotExist(Class.forName(cardPath), inputs);
	}

	@Test(timeout = 100)
	public void testConstructorCardInitialization() throws Exception {

		Constructor<?> constructor = Class.forName(cardPath).getConstructor(Class.forName(colorPath));
		int r2 = (int) (Math.random() * 5);
		Object color = null;
		if (r2 == 0)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED");
		else if (r2 == 1)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN");
		else if (r2 == 2)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE");
		else if (r2 == 3)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW");
		else
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "WILD");

		Object b = constructor.newInstance(color);
		String[] varNames = { "color" };
		Object[] varValues = { color };
		testConstructorInitialization(b, varNames, varValues);
	}

	@Test(timeout = 100)
	public void testConstructorWildCard() throws ClassNotFoundException {
		Class[] inputs = {};
		testConstructorExists(Class.forName(wildCardPath), inputs);
	}

	@Test(timeout = 100)
	public void testConstructor2WildCard() throws ClassNotFoundException {
		Class[] inputs = { Class.forName(colorPath) };
		testConstructorDoesNotExist(Class.forName(wildCardPath), inputs);
	}

	@Test(timeout = 100)
	public void testConstructorWildCardInitialization() throws Exception {
		Constructor<?> constructor = Class.forName(wildCardPath).getConstructor();
		Object b = constructor.newInstance();
		String[] varNames = { "color" };
		Object[] varValues = { Enum.valueOf((Class<Enum>) Class.forName(colorPath), "WILD") };
		testConstructorInitialization(b, varNames, varValues);
	}

	@Test(timeout = 100)
	public void testConstructorChangeCard() throws ClassNotFoundException {
		Class[] inputs = {};
		testConstructorExists(Class.forName(changeCardPath), inputs);
	}

	@Test(timeout = 100)
	public void testConstructor2ChangeCard() throws ClassNotFoundException {
		Class[] inputs = { Class.forName(colorPath) };
		testConstructorDoesNotExist(Class.forName(changeCardPath), inputs);
	}

	@Test(timeout = 100)
	public void testConstructorChangeCardInitialization() throws Exception {
		Constructor<?> constructor = Class.forName(changeCardPath).getConstructor();
		Object b = constructor.newInstance();
		String[] varNames = { "color" };
		Object[] varValues = { Enum.valueOf((Class<Enum>) Class.forName(colorPath), "WILD") };
		testConstructorInitialization(b, varNames, varValues);
	}

	@Test(timeout = 100)
	public void testConstructorHitAndChangeCard() throws ClassNotFoundException {
		Class[] inputs = {};
		testConstructorExists(Class.forName(hitAndChangeCardPath), inputs);
	}

	@Test(timeout = 100)
	public void testConstructor2HitAndChangeCard() throws ClassNotFoundException {
		Class[] inputs = { Class.forName(colorPath) };
		testConstructorDoesNotExist(Class.forName(hitAndChangeCardPath), inputs);
	}

	@Test(timeout = 100)
	public void testConstructorHitAndChangeCardInitialization() throws Exception {
		Constructor<?> constructor = Class.forName(hitAndChangeCardPath).getConstructor();
		int randomHit = (int) (Math.random() * 5) + 4;
		String[] varNames = { "color", "hitNumber" };
		Object[] varValues = { Enum.valueOf((Class<Enum>) Class.forName(colorPath), "WILD"), randomHit };

		Object[] createdObjects = new Object[200];
		for (int i = 0; i < 200; i++) {
			Object b = constructor.newInstance();
			createdObjects[i] = b;
		}
		testConstructorInitializationHitAndChangeCards(createdObjects, varNames, varValues);
	}

	@Test(timeout = 100)
	public void testConstructorHitCard() throws ClassNotFoundException {
		Class[] inputs = { Class.forName(colorPath) };
		testConstructorExists(Class.forName(hitCardPath), inputs);
	}

	@Test(timeout = 100)
	public void testConstructor2HitCard() throws ClassNotFoundException {
		Class[] inputs = {};
		testConstructorDoesNotExist(Class.forName(hitCardPath), inputs);
	}

	@Test(timeout = 100)
	public void testConstructorHitCardInitialization() throws Exception {
		Constructor<?> constructor = Class.forName(hitCardPath).getConstructor(Class.forName(colorPath));
		int r2 = (int) (Math.random() * 5);
		Object color = null;
		if (r2 == 0)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED");
		else if (r2 == 1)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN");
		else if (r2 == 2)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE");
		else if (r2 == 3)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW");
		else
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "WILD");
		int randomHit = (int) (Math.random() * 5) + 4;
		String[] varNames = { "color", "hitNumber" };
		Object[] varValues = { color, randomHit };

		Object[] createdObjects = new Object[200];
		for (int i = 0; i < 200; i++) {
			Object b = constructor.newInstance(color);
			createdObjects[i] = b;
		}
		testConstructorInitializationHitCards(createdObjects, varNames, varValues);
	}

	@Test(timeout = 100)
	public void testConstructorActionCard() throws ClassNotFoundException {
		Class[] inputs = { Class.forName(colorPath) };
		testConstructorExists(Class.forName(actionCardPath), inputs);
	}

	@Test(timeout = 100)
	public void testConstructor2ActionCard() throws ClassNotFoundException {
		Class[] inputs = {};
		testConstructorDoesNotExist(Class.forName(actionCardPath), inputs);
	}

	@Test(timeout = 100)
	public void testConstructorActionCardInitialization() throws Exception {
		Constructor<?> constructor = Class.forName(actionCardPath).getConstructor(Class.forName(colorPath));
		int r2 = (int) (Math.random() * 5);
		Object color = null;
		if (r2 == 0)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED");
		else if (r2 == 1)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN");
		else if (r2 == 2)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE");
		else if (r2 == 3)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW");
		else
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "WILD");
		Object b = constructor.newInstance(color);
		String[] varNames = { "color" };
		Object[] varValues = { color };
		testConstructorInitialization(b, varNames, varValues);
	}

	@Test(timeout = 100)
	public void testConstructorColorCard() throws ClassNotFoundException {
		Class[] inputs = { Class.forName(colorPath) };
		testConstructorExists(Class.forName(colorCardPath), inputs);
	}

	@Test(timeout = 100)
	public void testConstructor2ColorCard() throws ClassNotFoundException {
		Class[] inputs = {};
		testConstructorDoesNotExist(Class.forName(colorCardPath), inputs);
	}

	@Test(timeout = 100)
	public void testConstructorColorCardInitialization() throws Exception {
		Constructor<?> constructor = Class.forName(colorCardPath).getConstructor(Class.forName(colorPath));
		int r2 = (int) (Math.random() * 5);
		Object color = null;
		if (r2 == 0)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED");
		else if (r2 == 1)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN");
		else if (r2 == 2)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE");
		else if (r2 == 3)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW");
		else
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "WILD");
		Object b = constructor.newInstance(color);
		String[] varNames = { "color" };
		Object[] varValues = { color };
		testConstructorInitialization(b, varNames, varValues);
	}

	@Test(timeout = 100)
	public void testConstructorSkipCard() throws ClassNotFoundException {
		Class[] inputs = { Class.forName(colorPath) };
		testConstructorExists(Class.forName(skipCardPath), inputs);
	}

	@Test(timeout = 100)
	public void testConstructor2SkipCard() throws ClassNotFoundException {
		Class[] inputs = {};
		testConstructorDoesNotExist(Class.forName(skipCardPath), inputs);
	}

	@Test(timeout = 100)
	public void testConstructorSkipCardInitialization() throws Exception {
		Constructor<?> constructor = Class.forName(skipCardPath).getConstructor(Class.forName(colorPath));
		int r2 = (int) (Math.random() * 5);
		Object color = null;
		if (r2 == 0)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED");
		else if (r2 == 1)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN");
		else if (r2 == 2)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE");
		else if (r2 == 3)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW");
		else
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "WILD");
		Object b = constructor.newInstance(color);
		String[] varNames = { "color" };
		Object[] varValues = { color };
		testConstructorInitialization(b, varNames, varValues);
	}

	@Test(timeout = 100)
	public void testConstructorNumberCard() throws ClassNotFoundException {
		Class[] inputs = { Class.forName(colorPath) };
		testConstructorDoesNotExist(Class.forName(numberCardPath), inputs);
	}

	@Test(timeout = 100)
	public void testConstructor2NumberCard() throws ClassNotFoundException {
		Class[] inputs = {};
		testConstructorDoesNotExist(Class.forName(numberCardPath), inputs);
	}

	@Test(timeout = 100)
	public void testConstructor3NumberCard() throws ClassNotFoundException {
		Class[] inputs = { int.class, Class.forName(colorPath) };
		testConstructorExists(Class.forName(numberCardPath), inputs);
	}

	@Test(timeout = 100)
	public void testConstructorNumberCardInitialization() throws Exception {
		Constructor<?> constructor = Class.forName(numberCardPath).getConstructor(int.class, Class.forName(colorPath));
		int r2 = (int) (Math.random() * 5);
		Object color = null;
		if (r2 == 0)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED");
		else if (r2 == 1)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN");
		else if (r2 == 2)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE");
		else if (r2 == 3)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW");
		else
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "WILD");
		int r1 = (int) (Math.random() * 10);
		Object b = constructor.newInstance(r1, color);
		String[] varNames = { "number", "color" };
		Object[] varValues = { r1, color };
		testConstructorInitialization(b, varNames, varValues);
	}

	@Test(timeout = 100)
	public void testConstructor2Game() throws ClassNotFoundException {
		Class[] inputs = {};
		testConstructorDoesNotExist(Class.forName(gamePath), inputs);
	}

	@Test(timeout = 100)
	public void testConstructorGame() throws ClassNotFoundException {
		Class[] inputs = { ArrayList.class };
		testConstructorExists(Class.forName(gamePath), inputs);
	}

	@Test(timeout = 1000)
	public void testInstanceVariablePlayersIsPresentInClassGame() throws Exception {
		testInstanceVariableIsPresent(Class.forName(gamePath), "players", true);
	}

	@Test(timeout = 1000)
	public void testInstanceVariablePlayersIsPrivateInClassGame() throws Exception {
		testInstanceVariableIsPrivate(Class.forName(gamePath), "players");
	}

	@Test(timeout = 1000)
	public void testGetterForInstanceVariablePlayersExistsInClassGame() throws Exception {
		testGetterMethodExistsInClass(Class.forName(gamePath), "getPlayers", ArrayList.class, true);
	}

	@Test(timeout = 100)
	public void testGamePlayersGetterLogic() throws Exception {
		Constructor<?> constructor = Class.forName(gamePath).getConstructor(ArrayList.class);
		int r = (int) (Math.random() * 100);
		int r1 = (int) (Math.random() * 1000) + r;

		Constructor<?> cPlayer = Class.forName(playerPath).getConstructor(String.class);
		Object firstPlayer = cPlayer.newInstance("RDJ" + r1);
		Object secondPlayer = cPlayer.newInstance("Chris Evans" + r);
		ArrayList<Object> xr = new ArrayList<>();
		xr.add(firstPlayer);
		xr.add(secondPlayer);
		Object b = constructor.newInstance(xr);

		Field f = Class.forName(gamePath).getDeclaredField("players");
		f.setAccessible(true);
		f.set(b, new ArrayList<>());
		testGetterLogic(b, "players", xr);
	}

	@Test(timeout = 1000)
	public void testSetterForInstanceVariablePlayersExistsInClassGame() throws Exception {
		testSetterMethodExistsInClass(Class.forName(gamePath), "setPlayers", ArrayList.class, false);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableDeckIsPresentInClassGame() throws Exception {
		testInstanceVariableIsPresent(Class.forName(gamePath), "deck", true);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableDeckIsPrivateInClassGame() throws Exception {
		testInstanceVariableIsPrivate(Class.forName(gamePath), "deck");
	}

	@Test(timeout = 1000)
	public void testGetterForInstanceVariableDeckExistsInClassGame() throws Exception {
		testGetterMethodExistsInClass(Class.forName(gamePath), "getDeck", ArrayList.class, true);
	}

	@Test(timeout = 100)
	public void testGameDeckGetterLogic() throws Exception {
		Constructor<?> constructor = Class.forName(gamePath).getConstructor(ArrayList.class);

		int r2 = (int) (Math.random() * 5);
		Object cc1 = null;
		if (r2 == 0)
			cc1 = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED");
		else if (r2 == 1)
			cc1 = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN");
		else if (r2 == 2)
			cc1 = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE");
		else if (r2 == 3)
			cc1 = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW");
		else
			cc1 = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "WILD");

		int r3 = (int) (Math.random() * 5);
		Object cc2 = null;
		if (r2 == 0)
			cc2 = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED");
		else if (r2 == 1)
			cc2 = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN");
		else if (r2 == 2)
			cc2 = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE");
		else if (r2 == 3)
			cc2 = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW");
		else
			cc2 = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "WILD");

		Constructor<?> cPlayer = Class.forName(playerPath).getConstructor(String.class);
		Object firstPlayer = cPlayer.newInstance("RDJ");
		Object secondPlayer = cPlayer.newInstance("Chris Evans");
		ArrayList<Object> xr = new ArrayList<>();
		xr.add(firstPlayer);
		xr.add(secondPlayer);

		Constructor<?> cCard = Class.forName(cardPath).getConstructor(Class.forName(colorPath));
		ArrayList<Object> xr2 = new ArrayList<>();
		Object c1 = cCard.newInstance(cc1);
		Object c2 = cCard.newInstance(cc2);
		xr2.add(c1);
		xr2.add(c2);

		Object b = constructor.newInstance(xr);
		Field f = Class.forName(gamePath).getDeclaredField("deck");
		f.setAccessible(true);
		f.set(b, xr2);
		testGetterLogic(b, "deck", xr2);
	}

	@Test(timeout = 1000)
	public void testSetterForInstanceVariableDeckExistsInClassGame() throws Exception {
		testSetterMethodExistsInClass(Class.forName(gamePath), "setDeck", ArrayList.class, false);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableDiscardPileIsPresentInClassGame() throws Exception {
		testInstanceVariableIsPresent(Class.forName(gamePath), "discardPile", true);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableDiscardPileIsPrivateInClassGame() throws Exception {
		testInstanceVariableIsPrivate(Class.forName(gamePath), "discardPile");
	}

	@Test(timeout = 1000)
	public void testGetterForInstanceVariableDiscardPileExistsInClassGame() throws Exception {
		testGetterMethodExistsInClass(Class.forName(gamePath), "getDiscardPile", ArrayList.class, true);
	}

	@Test(timeout = 100)
	public void testGameDiscardPileGetterLogic() throws Exception {
		Constructor<?> constructor = Class.forName(gamePath).getConstructor(ArrayList.class);

		int r2 = (int) (Math.random() * 5);
		Object cc1 = null;
		if (r2 == 0)
			cc1 = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED");
		else if (r2 == 1)
			cc1 = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN");
		else if (r2 == 2)
			cc1 = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE");
		else if (r2 == 3)
			cc1 = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW");
		else
			cc1 = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "WILD");

		int r3 = (int) (Math.random() * 5);
		Object cc2 = null;
		if (r2 == 0)
			cc2 = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED");
		else if (r2 == 1)
			cc2 = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN");
		else if (r2 == 2)
			cc2 = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE");
		else if (r2 == 3)
			cc2 = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW");
		else
			cc2 = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "WILD");

		Constructor<?> cPlayer = Class.forName(playerPath).getConstructor(String.class);
		Object firstPlayer = cPlayer.newInstance("RDJ");
		Object secondPlayer = cPlayer.newInstance("Chris Evans");
		ArrayList<Object> xr = new ArrayList<>();
		xr.add(firstPlayer);
		xr.add(secondPlayer);

		Constructor<?> cCard = Class.forName(cardPath).getConstructor(Class.forName(colorPath));
		ArrayList<Object> xr2 = new ArrayList<>();
		Object c1 = cCard.newInstance(cc1);
		Object c2 = cCard.newInstance(cc2);
		xr2.add(c1);
		xr2.add(c2);

		Object b = constructor.newInstance(xr);
		Field f = Class.forName(gamePath).getDeclaredField("deck");
		f.setAccessible(true);
		f.set(b, xr2);

		testGetterLogic(b, "discardPile", xr2);
	}

	@Test(timeout = 1000)
	public void testSetterForInstanceVariableDiscardPileExistsInClassGame() throws Exception {
		testSetterMethodExistsInClass(Class.forName(gamePath), "setDiscardPile", ArrayList.class, false);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableCurrentDrawnIsPresentInClassGame() throws Exception {
		testInstanceVariableIsPresent(Class.forName(gamePath), "currentDrawn", true);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableCurrentDrawnIsPrivateInClassGame() throws Exception {
		testInstanceVariableIsPrivate(Class.forName(gamePath), "currentDrawn");
	}

	@Test(timeout = 1000)
	public void testGetterForInstanceVariableCurrentDrawnExistsInClassGame() throws Exception {
		testGetterMethodExistsInClass(Class.forName(gamePath), "getCurrentDrawn", int.class, true);
	}

	@Test(timeout = 100)
	public void testGameCurrentDrawnGetterLogic() throws Exception {
		Constructor<?> constructor = Class.forName(gamePath).getConstructor(ArrayList.class);

		Constructor<?> cPlayer = Class.forName(playerPath).getConstructor(String.class);
		Object firstPlayer = cPlayer.newInstance("RDJ");
		Object secondPlayer = cPlayer.newInstance("Chris Evans");
		ArrayList<Object> xr = new ArrayList<>();
		xr.add(firstPlayer);
		xr.add(secondPlayer);

		int r2 = (int) (Math.random() * 1000);

		Object b = constructor.newInstance(xr);
		Field f = Class.forName(gamePath).getDeclaredField("currentDrawn");
		f.setAccessible(true);
		f.set(b, r2);

		testGetterLogic(b, "currentDrawn", r2);
	}

	@Test(timeout = 1000)
	public void testSetterForInstanceVariableCurrentDrawnExistsInClassGame() throws Exception {
		testSetterMethodExistsInClass(Class.forName(gamePath), "setCurrentDrawn", int.class, false);
	}

	@Test(timeout = 1000)
	public void testBuildDeckInClassGame() throws Exception {
		ArrayList<Object> d = deckHelper();
		ArrayList<String> s1 = deckHelper2(d);
		Constructor<?> constructor = Class.forName(gamePath).getConstructor(ArrayList.class);

		Constructor<?> cPlayer = Class.forName(playerPath).getConstructor(String.class);
		Object firstPlayer = cPlayer.newInstance("RDJ");
		Object secondPlayer = cPlayer.newInstance("Chris Evans");
		ArrayList<Object> xr = new ArrayList<>();
		xr.add(firstPlayer);
		xr.add(secondPlayer);

		int r2 = (int) (Math.random() * 1000);

		Object b = constructor.newInstance(xr);
		Method m2 = b.getClass().getDeclaredMethod("buildDeck");
		m2.setAccessible(true);
		m2.invoke(b);

		Field f = Class.forName(gamePath).getDeclaredField("deck");
		f.setAccessible(true);
		ArrayList<Object> a2 = (ArrayList<Object>) f.get(b);
		ArrayList<String> s2 = deckHelper2(a2);

		assertTrue("The Game class should build the deck correctly.", arePermutation(s1, s2));
	}

	@Test(timeout = 100)
	public void testConstructorGameInitialization() throws Exception {
		Constructor<?> constructor = Class.forName(gamePath).getConstructor(ArrayList.class);
		int r = (int) (Math.random() * 100);
		int r1 = (int) (Math.random() * 1000) + r;
		Constructor<?> cPlayer = Class.forName(playerPath).getConstructor(String.class);
		Object firstPlayer = cPlayer.newInstance("RDJ" + r1);
		Object secondPlayer = cPlayer.newInstance("Chris Evans" + r);
		ArrayList<Object> xr = new ArrayList<>();
		xr.add(firstPlayer);
		xr.add(secondPlayer);

		Object b = constructor.newInstance(xr);
		String[] varNames = { "players", "currentDrawn" };
		Object[] varValues = { xr, 0 };
		testConstructorInitialization(b, varNames, varValues);
	}

	@Test(timeout = 1000)
	public void testBuildDeckShufflesDeck()
			throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Constructor<?> constructor = Class.forName(gamePath).getConstructor(ArrayList.class);

		Constructor<?> cPlayer = Class.forName(playerPath).getConstructor(String.class);
		Object firstPlayer = cPlayer.newInstance("RDJ");
		Object secondPlayer = cPlayer.newInstance("Chris Evans");
		ArrayList<Object> xr = new ArrayList<>();
		xr.add(firstPlayer);
		xr.add(secondPlayer);

		int r2 = (int) (Math.random() * 1000);

		String[] s = new String[200];
		for (int i = 0; i < 200; i++) {
			Object b = constructor.newInstance(xr);
			Method m2 = b.getClass().getDeclaredMethod("buildDeck");
			m2.setAccessible(true);
			m2.invoke(b);
			Field f = Class.forName(gamePath).getDeclaredField("deck");
			f.setAccessible(true);
			ArrayList<Object> a2 = (ArrayList<Object>) f.get(b);
			s[i] = deckHelper3(a2.get(0));
		}
		deckIsShuffledHelper(s);
	}

	@Test(timeout = 1000)
	public void testGameConstructorCallsBuildDeck()
			throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Constructor<?> constructor = Class.forName(gamePath).getConstructor(ArrayList.class);

		Constructor<?> cPlayer = Class.forName(playerPath).getConstructor(String.class);
		Object firstPlayer = cPlayer.newInstance("RDJ");
		Object secondPlayer = cPlayer.newInstance("Chris Evans");
		ArrayList<Object> xr = new ArrayList<>();
		xr.add(firstPlayer);
		xr.add(secondPlayer);

		Object b = constructor.newInstance(xr);
		Field f = Class.forName(gamePath).getDeclaredField("deck");
		f.setAccessible(true);

		assertTrue("The Game constructor should also build the deck.", ((ArrayList<Object>) f.get(b)).size() != 0);
	}

	@Test(timeout = 1000)
	public void testGameConstructorCallsDistribute()
			throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Constructor<?> constructor = Class.forName(gamePath).getConstructor(ArrayList.class);

		Constructor<?> cPlayer = Class.forName(playerPath).getConstructor(String.class);
		Object firstPlayer = cPlayer.newInstance("RDJ");
		Object secondPlayer = cPlayer.newInstance("Chris Evans");
		ArrayList<Object> xr = new ArrayList<>();
		xr.add(firstPlayer);
		xr.add(secondPlayer);

		Object b = constructor.newInstance(xr);
		Field f = Class.forName(gamePath).getDeclaredField("players");
		f.setAccessible(true);

		assertTrue("The Game constructor should distribute 7 cards to each player.", checkPlayers(f.get(b)));
	}
	
	@Test(timeout = 1000)
	public void testDistributeWorkingCorrectly()
			throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Constructor<?> constructor = Class.forName(gamePath).getConstructor(ArrayList.class);

		Constructor<?> cPlayer = Class.forName(playerPath).getConstructor(String.class);
		ArrayList<Object> a = new ArrayList<Object>();
		Object firstPlayer = cPlayer.newInstance("RDJ");
		Object secondPlayer = cPlayer.newInstance("Chris Evans");
		ArrayList<Object> xr = new ArrayList<>();
		xr.add(firstPlayer);
		xr.add(secondPlayer);

		Object b = constructor.newInstance(a);
		Field f = Class.forName(gamePath).getDeclaredField("players");
		f.setAccessible(true);
		f.set(b, xr);
		
		Method m2 = b.getClass().getDeclaredMethod("distribute");
		m2.setAccessible(true);
		m2.invoke(b);

		assertTrue("The Distribute method should distribute 7 cards to each player.", checkPlayers(f.get(b)));
	}
	
	@Test(timeout = 1000)
	public void testGameConstructorInitializeDiscardPile()
			throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Constructor<?> constructor = Class.forName(gamePath).getConstructor(ArrayList.class);

		Constructor<?> cPlayer = Class.forName(playerPath).getConstructor(String.class);
		ArrayList<Object> a = new ArrayList<Object>();
		Object firstPlayer = cPlayer.newInstance("RDJ");
		Object secondPlayer = cPlayer.newInstance("Chris Evans");
		ArrayList<Object> xr = new ArrayList<>();
		xr.add(firstPlayer);
		xr.add(secondPlayer);

		Object b = constructor.newInstance(a);
		
		Field f = Class.forName(gamePath).getDeclaredField("discardPile");
		f.setAccessible(true);

		assertTrue("The Game Constructor should initialize the discard pile correctly.", ((ArrayList<Object>)f.get(b)).size() != 0);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private boolean checkPlayers(Object object) throws NoSuchFieldException, SecurityException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		ArrayList<Object> players = (ArrayList<Object>) object;
		Field f = Class.forName(playerPath).getDeclaredField("hand");
		f.setAccessible(true);
		boolean x = true;
		for (Object object2 : players) {
			if(((ArrayList<Object>)f.get(object2)).size() != 7)
				x = false;
		}
		return x;
	}

	public M1PublicTests() {
		// TODO Auto-generated constructor stub
	}

	private void testClassIsSubclass(Class subClass, Class superClass) {
		assertEquals(subClass.getSimpleName() + " class should be a subclass from " + superClass.getSimpleName() + ".",
				superClass, subClass.getSuperclass());
	}

	private void testSetterAbsentInSubclasses(String varName, String[] subclasses)
			throws SecurityException, ClassNotFoundException {
		String methodName = "set" + varName.substring(0, 1).toUpperCase() + varName.substring(1);
		boolean methodIsInSubclasses = false;
		for (String subclass : subclasses) {
			Method[] methods = Class.forName(subclass).getDeclaredMethods();
			methodIsInSubclasses = methodIsInSubclasses || containsMethodName(methods, methodName);

		}
		assertFalse("The " + methodName + " method should not be implemented in a subclasses.", methodIsInSubclasses);
	}

	private void testGetterAbsentInSubclasses(String varName, String[] subclasses, Class type)
			throws SecurityException, ClassNotFoundException {
		String methodName = "get" + varName.substring(0, 1).toUpperCase() + varName.substring(1);
		if (type == boolean.class) {
			methodName = "is" + varName.substring(0, 1).toUpperCase() + varName.substring(1);
		}
		boolean methodIsInSubclasses = false;
		for (String subclass : subclasses) {
			Method[] methods = Class.forName(subclass).getDeclaredMethods();
			methodIsInSubclasses = methodIsInSubclasses || containsMethodName(methods, methodName);

		}
		assertFalse("The " + methodName + " method should not be implemented in subclasses.", methodIsInSubclasses);
	}

	private void testIsEnum(Class aClass) {

		assertEquals(aClass.getName() + " should be an Enum", true, aClass.isEnum());

	}

	private void testStaticVariableIsPrivate(Class aClass, String varName)
			throws NoSuchFieldException, SecurityException {
		Field f = aClass.getDeclaredField(varName);
		int modifiers = f.getModifiers();
		assertTrue(f.getName() + " variable in calss Game should be private", (Modifier.isPrivate(modifiers)));

	}

	private static boolean containsMethodName(Method[] methods, String name) {
		for (Method method : methods) {
			if (method.getName().equals(name))
				return true;
		}
		return false;
	}

	private static void testEnumValues(String name, String path, String[] value) {

		try {
			Class aClass = Class.forName(path);
			for (int i = 0; i < value.length; i++) {
				try {
					Enum.valueOf((Class<Enum>) aClass, value[i]);
				} catch (IllegalArgumentException e) {
					fail(aClass.getSimpleName() + " enum can be " + value[i]);
				}
			}
		} catch (ClassNotFoundException e1) {

			fail("There should be an enum called " + name + "in package " + path);
		}

	}

	private void testInstanceVariableIsPresent(Class aClass, String varName, boolean implementedVar)
			throws SecurityException {
		boolean thrown = false;
		try {
			aClass.getDeclaredField(varName);
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		if (implementedVar) {
			assertFalse(
					"There should be \"" + varName + "\" instance variable in class " + aClass.getSimpleName() + ".",
					thrown);
		} else {
			assertTrue("The instance variable \"" + varName + "\" should not be declared in class "
					+ aClass.getSimpleName() + ".", thrown);
		}
	}

	private void testInstanceVariableOfType(Class aClass, String varName, Class expectedType) {
		Field f = null;
		boolean b = true;
		try {
			f = aClass.getDeclaredField(varName);
		} catch (NoSuchFieldException e) {
			b = false;
		}
		if (b) {
			Class varType = f.getType();
			assertEquals("The attribute " + varType.getSimpleName() + " of instance variable: " + varName
					+ " should be of the type " + expectedType.getSimpleName(), expectedType, varType);
		} else {
			assertTrue("The instance variable \"" + varName + "\" should be declared in class " + aClass.getSimpleName()
					+ ".", false);
		}

	}

	private void testInstanceVariableIsPrivate(Class aClass, String varName)
			throws NoSuchFieldException, SecurityException {
		Field f = aClass.getDeclaredField(varName);
		boolean b = 2 == f.getModifiers();
		assertTrue("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
				+ " should not be accessed outside that class.", b);
	}

	private void testGetterMethodExistsInClass(Class aClass, String methodName, Class returnedType,
			boolean readvariable) {
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName);
		} catch (NoSuchMethodException e) {
			found = false;
		}

		String varName = "";
		if (returnedType == boolean.class)
			varName = methodName.substring(2, 3).toLowerCase() + methodName.substring(3);
		else
			varName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
		if (readvariable) {
			assertTrue("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
					+ " is a READ variable.", found);
			assertTrue("Incorrect return type for " + methodName + " method in " + aClass.getSimpleName() + " class.",
					m.getReturnType().isAssignableFrom(returnedType));
		} else {
			assertFalse("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
					+ " is not a READ variable.", found);
		}

	}

	private void testGetterLogic(Object createdObject, String name, Object value) throws Exception {

		Field f = null;
		Class curr = createdObject.getClass();

		while (f == null) {

			if (curr == Object.class)
				fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \""
						+ name + "\".");
			try {
				f = curr.getDeclaredField(name);
			} catch (NoSuchFieldException e) {
				curr = curr.getSuperclass();
			}

		}

		f.setAccessible(true);
		f.set(createdObject, value);

		Character c = name.charAt(0);

		String methodName = "get" + Character.toUpperCase(c) + name.substring(1, name.length());

		if (value.getClass().equals(Boolean.class))
			methodName = "is" + Character.toUpperCase(c) + name.substring(1, name.length());

		Method m = createdObject.getClass().getMethod(methodName);
		assertEquals(
				"The method \"" + methodName + "\" in class " + createdObject.getClass().getSimpleName()
						+ " should return the correct value of variable \"" + name + "\".",
				value, m.invoke(createdObject));

	}

	private void testSetterMethodExistsInClass(Class aClass, String methodName, Class inputType,
			boolean writeVariable) {

		Method[] methods = aClass.getDeclaredMethods();
		String varName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
		if (writeVariable) {
			assertTrue("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
					+ " is a WRITE variable.", containsMethodName(methods, methodName));
		} else {
			assertFalse("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
					+ " is not a WRITE variable.", containsMethodName(methods, methodName));
			return;
		}
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName, inputType);
		} catch (NoSuchMethodException e) {
			found = false;
		}

		assertTrue(aClass.getSimpleName() + " class should have " + methodName + " method that takes one "
				+ inputType.getSimpleName() + " parameter.", found);

		assertTrue("Incorrect return type for " + methodName + " method in " + aClass.getSimpleName() + ".",
				m.getReturnType().equals(Void.TYPE));

	}

	private void testSetterLogic(Object createdObject, String name, Object setValue, Object expectedValue, Class type)
			throws Exception {

		Field f = null;
		Class curr = createdObject.getClass();

		while (f == null) {

			if (curr == Object.class)
				fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \""
						+ name + "\".");
			try {
				f = curr.getDeclaredField(name);
			} catch (NoSuchFieldException e) {
				curr = curr.getSuperclass();
			}

		}

		f.setAccessible(true);

		Character c = name.charAt(0);
		String methodName = "set" + Character.toUpperCase(c) + name.substring(1, name.length());
		Method m = createdObject.getClass().getMethod(methodName, type);
		m.invoke(createdObject, setValue);

		assertEquals(
				"The method \"" + methodName + "\" in class " + createdObject.getClass().getSimpleName()
						+ " should set the correct value of variable \"" + name + "\".",
				expectedValue, f.get(createdObject));

	}

	private void testConstructorExists(Class aClass, Class[] inputs) {
		boolean thrown = false;
		try {
			aClass.getConstructor(inputs);
		} catch (NoSuchMethodException e) {
			thrown = true;
		}

		if (inputs.length > 0) {
			String msg = "";
			int i = 0;
			do {
				msg += inputs[i].getSimpleName() + " and ";
				i++;
			} while (i < inputs.length);

			msg = msg.substring(0, msg.length() - 4);

			assertFalse(
					"Missing constructor with " + msg + " parameter" + (inputs.length > 1 ? "s" : "") + " in "
							+ aClass.getSimpleName() + " class.",

					thrown);
		} else
			assertFalse("Missing constructor with zero parameters in " + aClass.getSimpleName() + " class.",

					thrown);

	}

	private void testConstructorInitialization(Object createdObject, String[] names, Object[] values)
			throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException {

		for (int i = 0; i < names.length; i++) {

			Field f = null;
			Class curr = createdObject.getClass();
			String currName = names[i];
			Object currValue = values[i];

			while (f == null) {

				if (curr == Object.class)
					fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \""
							+ currName + "\".");
				try {
					f = curr.getDeclaredField(currName);
				} catch (NoSuchFieldException e) {
					curr = curr.getSuperclass();
				}

			}

			f.setAccessible(true);
			assertEquals(
					"The constructor of the " + createdObject.getClass().getSimpleName()
							+ " class should initialize the instance variable \"" + currName + "\" correctly.",
					currValue, f.get(createdObject));
		}

	}

	private void testConstructorDoesNotExist(Class aClass, Class[] inputs) {
		boolean thrown = false;
		try {
			aClass.getConstructor(inputs);
		} catch (NoSuchMethodException e) {
			thrown = true;
		}
		if (inputs.length > 0) {
			String msg = "";
			int i = 0;
			do {
				msg += inputs[i].getSimpleName() + " and ";
				i++;
			} while (i < inputs.length);
			msg = msg.substring(0, msg.length() - 4);
			assertTrue("There should not be a  constructor with " + msg + " parameter" + (inputs.length > 1 ? "s" : "")
					+ " in " + aClass.getSimpleName() + " class.", thrown);
		} else
			assertTrue(
					"There should not be a constructor with zero parameters in " + aClass.getSimpleName() + " class.",
					thrown);
	}

	private void testConstructorInitializationHitAndChangeCards(Object[] createdObject, String[] names, Object[] values)
			throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException {

		Integer[] currentHitValues = new Integer[createdObject.length];
		for (int j = 0; j < createdObject.length; j++) {
			for (int i = 0; i < names.length; i++) {
				Field f = null;
				Class curr = createdObject[j].getClass();
				String currName = names[i];
				Object currValue = values[i];

				while (f == null) {

					if (curr == Object.class)
						fail("Class " + createdObject[j].getClass().getSimpleName()
								+ " should have the instance variable \"" + currName + "\".");
					try {
						f = curr.getDeclaredField(currName);
					} catch (NoSuchFieldException e) {
						curr = curr.getSuperclass();
					}

				}
				f.setAccessible(true);
				if (!names[i].equals("hitNumber")) {
					assertEquals(
							"The constructor of the " + createdObject[j].getClass().getSimpleName()
									+ " class should initialize the instance variable \"" + currName + "\" correctly.",
							currValue, f.get(createdObject[j]));
				}
				if (names[i].equals("hitNumber")) {
					currentHitValues[j] = (int) f.get(createdObject[j]);
					int num = (int) f.get(createdObject[j]);
					if (!(num >= 4 && num < 9)) {
						assertEquals("The constructor of the " + createdObject[j].getClass().getSimpleName()
								+ " class should initialize the instance variable \"" + currName
								+ "\" correctly. It should be a random number between 4(inclusive) and 8(inclusive).",
								currValue, num);

					}
				}
			}

		}

		String expected = "{";
		for (int i = 0; i < 3; i++) {
			expected = expected + ((int) (Math.random() * 5) + 4) + ", ";
		}
		expected = expected + "...}";
		Set<Integer> s = new HashSet<Integer>(Arrays.asList(currentHitValues));
		String tmp = "{";
		for (int i = 0; i < 3; i++) {
			tmp = tmp + currentHitValues[i] + ", ";
		}
		tmp = tmp + "...}";
		if (!((s.size() <= currentHitValues.length && s.size() >= currentHitValues.length * 0.02))) {
			assertEquals("The constructor of the " + createdObject[0].getClass().getSimpleName()
					+ " class should initialize the instance variable \"" + "hitNumber"
					+ "\" correctly. It should be a random number not a constant.", expected, tmp);

		}

	}

	private void testConstructorInitializationHitCards(Object[] createdObject, String[] names, Object[] values)
			throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException {

		Integer[] currentHitValues = new Integer[createdObject.length];
		for (int j = 0; j < createdObject.length; j++) {
			for (int i = 0; i < names.length; i++) {
				Field f = null;
				Class curr = createdObject[j].getClass();
				String currName = names[i];
				Object currValue = values[i];

				while (f == null) {

					if (curr == Object.class)
						fail("Class " + createdObject[j].getClass().getSimpleName()
								+ " should have the instance variable \"" + currName + "\".");
					try {
						f = curr.getDeclaredField(currName);
					} catch (NoSuchFieldException e) {
						curr = curr.getSuperclass();
					}

				}
				f.setAccessible(true);
				if (!names[i].equals("hitNumber")) {
					assertEquals(
							"The constructor of the " + createdObject[j].getClass().getSimpleName()
									+ " class should initialize the instance variable \"" + currName + "\" correctly.",
							currValue, f.get(createdObject[j]));
				}
				if (names[i].equals("hitNumber")) {
					currentHitValues[j] = (int) f.get(createdObject[j]);
					int num = (int) f.get(createdObject[j]);
					if (!(num >= 1 && num < 6)) {
						assertEquals("The constructor of the " + createdObject[j].getClass().getSimpleName()
								+ " class should initialize the instance variable \"" + currName
								+ "\" correctly. It should be a random number between 4(inclusive) and 8(inclusive).",
								currValue, num);

					}
				}
			}

		}

		String expected = "{";
		for (int i = 0; i < 3; i++) {
			expected = expected + ((int) (Math.random() * 5) + 1) + ", ";
		}
		expected = expected + "...}";
		Set<Integer> s = new HashSet<Integer>(Arrays.asList(currentHitValues));
		String tmp = "{";
		for (int i = 0; i < 3; i++) {
			tmp = tmp + currentHitValues[i] + ", ";
		}
		tmp = tmp + "...}";
		if (!((s.size() <= currentHitValues.length && s.size() >= currentHitValues.length * 0.02))) {
			assertEquals("The constructor of the " + createdObject[0].getClass().getSimpleName()
					+ " class should initialize the instance variable \"" + "hitNumber"
					+ "\" correctly. It should be a random number not a constant.", expected, tmp);

		}

	}

	private static boolean arePermutation(ArrayList<String> str1, ArrayList<String> str2) {

		int n1 = str1.size();
		int n2 = str2.size();

		if (n1 != n2)
			return false;

		Collections.sort(str1);
		Collections.sort(str2);

		for (int i = 0; i < n1; i++)
			if (!(str1.get(i).equals(str2.get(i))))
				return false;

		return true;
	}

	private ArrayList<Object> deckHelper()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			ClassNotFoundException, NoSuchMethodException, SecurityException {
		ArrayList<Object> d = new ArrayList<>();
		Constructor<?> chgCard = Class.forName(changeCardPath).getConstructor();
		Constructor<?> hitNChgCard = Class.forName(hitAndChangeCardPath).getConstructor();

		Constructor<?> numCard = Class.forName(numberCardPath).getConstructor(int.class, Class.forName(colorPath));
		Constructor<?> hitCard = Class.forName(hitCardPath).getConstructor(Class.forName(colorPath));
		Constructor<?> skipCard = Class.forName(skipCardPath).getConstructor(Class.forName(colorPath));

		d.add(numCard.newInstance(0, Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED")));
		d.add(numCard.newInstance(0, Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN")));
		d.add(numCard.newInstance(0, Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE")));
		d.add(numCard.newInstance(0, Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW")));
		for (int j = 0; j < 2; j++) {
			for (int i = 1; i <= 9; i++) {
				d.add(numCard.newInstance(i, Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED")));
				d.add(numCard.newInstance(i, Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN")));
				d.add(numCard.newInstance(i, Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE")));
				d.add(numCard.newInstance(i, Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW")));
			}
			d.add(hitCard.newInstance(Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED")));
			d.add(hitCard.newInstance(Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN")));
			d.add(hitCard.newInstance(Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE")));
			d.add(hitCard.newInstance(Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW")));

			d.add(skipCard.newInstance(Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED")));
			d.add(skipCard.newInstance(Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN")));
			d.add(skipCard.newInstance(Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE")));
			d.add(skipCard.newInstance(Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW")));

			d.add(chgCard.newInstance());
			d.add(hitNChgCard.newInstance());
			d.add(chgCard.newInstance());
			d.add(hitNChgCard.newInstance());
		}
		return d;
	}

	private ArrayList<String> deckHelper2(ArrayList<Object> a) throws NoSuchFieldException, SecurityException,
			ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		ArrayList<String> s = new ArrayList<>();

		for (int i = 0; i < a.size(); i++) {
			String curr = a.get(i).getClass().getSimpleName();
			Field f = Class.forName(cardPath).getDeclaredField("color");
			f.setAccessible(true);
			curr = curr + f.get(a.get(i)).toString();
			if (a.get(i).getClass().equals(Class.forName(numberCardPath))) {
				Field f2 = Class.forName(numberCardPath).getDeclaredField("number");
				f2.setAccessible(true);
				curr = curr + f2.get(a.get(i)).toString();
			}
			s.add(curr);
		}

		return s;
	}

	public String deckHelper3(Object o) throws NoSuchFieldException, SecurityException, ClassNotFoundException,
			IllegalArgumentException, IllegalAccessException {
		String curr = o.getClass().getSimpleName();
		Field f = Class.forName(cardPath).getDeclaredField("color");
		f.setAccessible(true);
		curr = curr + f.get(o).toString();
		if (o.getClass().equals(Class.forName(numberCardPath))) {
			Field f2 = Class.forName(numberCardPath).getDeclaredField("number");
			f2.setAccessible(true);
			curr = curr + f2.get(o).toString();
		}
		return curr;
	}

	public void deckIsShuffledHelper(String[] in) {

		Set<String> s = new HashSet<String>(Arrays.asList(in));
		boolean thrown = false;
		if (s.size() > 0.2 * in.length) {
			thrown = true;
		}
		assertTrue("The build deck method should shuffle the deck.", thrown);
	}

}
