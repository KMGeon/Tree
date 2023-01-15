from fastapi import FastAPI, Request, Form
from fastapi.responses import HTMLResponse
from fastapi.staticfiles import StaticFiles
from fastapi.templating import Jinja2Templates
from pydantic.main import BaseModel
from starlette.responses import JSONResponse
from member_dao import MemberDao


app = FastAPI()

class Mem(BaseModel):
    m_seq: str
    m_name: str = None
    tel: str = None
    army_yn: str = None

app.mount("/static", StaticFiles(directory="static"), name="static")
templates = Jinja2Templates(directory="templates")

@app.get("/mem", response_class=HTMLResponse)
async def mem_list(request: Request):
    return templates.TemplateResponse("mem.html", {"request": request})

@app.get("/mem_selects")
async def mem_selects():
    ed = MemberDao()
    emps = ed.selects()
    return JSONResponse(emps)

@app.post("/mem_select")
async def mem_select(mem:Mem):
    ed = MemberDao()
    emp = ed.select(mem.m_seq)
    return JSONResponse(emp)

@app.post("/mem_insert")
async def mem_insert(mem:Mem):
    ed = MemberDao()
    cnt = ed.insert(mem.m_name, mem.tel, mem.army_yn)
    return JSONResponse(cnt)

@app.post("/mem_update")
async def mem_update(mem:Mem):
    ed = MemberDao()
    cnt = ed.update(mem.m_seq, mem.m_name, mem.tel, mem.army_yn)
    return JSONResponse(cnt)

@app.post("/mem_delete")
async def mem_delete(mem:Mem):
    ed = MemberDao()
    cnt = ed.delete(mem.m_seq)
    return JSONResponse(cnt)



# uvicorn memberajax:app --reload