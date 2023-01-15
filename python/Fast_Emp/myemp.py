

from fastapi import FastAPI, Request
from fastapi.param_functions import Form
from fastapi.responses import HTMLResponse
from fastapi.staticfiles import StaticFiles
from fastapi.templating import Jinja2Templates

from emp_dao import EmpDao

app = FastAPI()

app.mount("/static", StaticFiles(directory="static"), name="static")

templates = Jinja2Templates(directory="templates")


@app.get("/", response_class=HTMLResponse)
@app.get("/emp_list", response_class=HTMLResponse)
async def emp_list(request: Request):
  
    return templates.TemplateResponse("emp_list.html", {"request": request})

# uvicorn myajax:app --reload

