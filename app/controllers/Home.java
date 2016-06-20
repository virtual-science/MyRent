package controllers;

import play.*;
import play.mvc.*;
import models.*;

public class Home extends Controller
{
public static void index()
{
	Logger.info("Landed in Welcome class");
	render();
}
}