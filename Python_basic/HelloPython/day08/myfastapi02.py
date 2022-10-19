from fastapi import FastAPI, Request
from fastapi.responses import HTMLResponse
from fastapi.staticfiles import StaticFiles
from fastapi.templating import Jinja2Templates

app = FastAPI()

app.mount("/static", StaticFiles(directory="static"), name="static")

templates = Jinja2Templates(directory="templates")


@app.get("/hello", response_class=HTMLResponse)
async def hello(request: Request):
    str = "패스트API"
    
    emps = [
        {"e_id":'1', "e_name":"1", "sex":"1", "addr":"1"},
        {"e_id":'2', "e_name":"2", "sex":"2", "addr":"2"},
        {"e_id":'3', "e_name":"3", "sex":"3", "addr":"3"}
        ]
    return templates.TemplateResponse("index.html", {"request": request, "str": str , "emps":emps})
