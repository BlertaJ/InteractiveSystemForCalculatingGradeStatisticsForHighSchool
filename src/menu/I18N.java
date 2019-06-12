package menu;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class I18N {

	public static ObjectProperty<Locale> locale;

	static {
		// static block
		locale = new SimpleObjectProperty<>(getDefault());

	}

	public static List<Locale> getLocales() {

		return new ArrayList<Locale>(Arrays.asList(Locale.ENGLISH, new Locale("al", "AL")));
	}

	public static Locale getDefault() {
		return Locale.ENGLISH;
	}

	public static Locale getLocale() {
		return locale.get();
	}

	public static ObjectProperty<Locale> localeProperty() {
		return locale;
	}

	public static void setLocale(Locale locale) {
		localeProperty().set(locale);
	}

	public static String get(String key, Object... args) {

		ResourceBundle bundle = ResourceBundle.getBundle("Translates.translates", getLocale()); // parametri i pare
																								// url-ja, parametri i
																								// dyte gjuha

		return MessageFormat.format(bundle.getString(key), args);

	}

	public static StringBinding createStringBinding(String key, Object... args) {
		return Bindings.createStringBinding(() -> get(key, args), locale);
	}

	public static Label getLabel(String key, Object... args) {
		Label label = new Label();
		label.textProperty().bind(createStringBinding(key, args));
		return label;
	}

	public static Button getButton(String key, Object... args) {
		Button btn = new Button();
		btn.textProperty().bind(createStringBinding(key, args));
		return btn;
	}

}
